/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upload;

import com.database.Database;
import com.domain.site.LanguageServlet;
import com.randomcodegenerator.CodeGenerator;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 *
 * @author Laurens Adema
 */
public class UploadServlet extends HttpServlet {

    private final boolean server = false;
    private final String hostName = "84.24.156.65";
    private final int port = 21;
    private final String username = "photoshop";
    private final String password = "pts4";
    private final int maxFileSize = 50000 * 1024;
    private final int maxMemSize = 50000 * 1024;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        File temp = File.createTempFile("temp", ".tmp");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(maxMemSize);
        factory.setRepository(temp);
        ServletFileUpload uploader = new ServletFileUpload(factory);
        uploader.setSizeMax(maxFileSize);

        if (!ServletFileUpload.isMultipartContent(request))
        {
            throw new ServletException("Content type is not multipart/form-data");
        }

        int groupID = -1;
        String price = null;
        String fileName = CodeGenerator.generateNumericCode(8) + "_" + CodeGenerator.generateNumericCode(8) + "_" + CodeGenerator.generateNumericCode(8);
        String pathLowRes = null;
        String pathHighRes = null;

        try
        {
            List<FileItem> fileItemsList = uploader.parseRequest(request);
            Iterator<FileItem> fileItemsIterator1 = fileItemsList.iterator();
            while (fileItemsIterator1.hasNext())
            {
                FileItem fileItem = fileItemsIterator1.next();

                if (fileItem.isFormField())
                {

                    String fieldname = fileItem.getFieldName();

                    if (fieldname.equals("selectGroupID"))
                    {
                        groupID = Integer.parseInt(fileItem.getString());
                    } else if (fieldname.equals("tbPrice"))
                    {
                        price = fileItem.getString();
                    }
                    fileItemsIterator1.remove();
                }
            }

            if (price != null && groupID > -1)
            {
                Iterator<FileItem> fileItemsIterator2 = fileItemsList.iterator();
                while (fileItemsIterator2.hasNext())
                {
                    FileItem fileItem = fileItemsIterator2.next();

                    // High res temp
                    System.out.println("Absolute Path at server=" + temp.getAbsolutePath());
                    fileItem.write(temp);

                    // High res upload
                    FTPClient ftp = null;
                    InputStream in = null;
                    try
                    {
                        ftp = new FTPClient();
                        if (server)
                        {
                            ftp.connect("localhost", port);
                        } else
                        {
                            ftp.connect(hostName, port);
                        }
                        if (ftp.login(username, password))
                        {

                            ftp.setFileType(FTP.BINARY_FILE_TYPE);

                            int reply = ftp.getReplyCode();
                            System.out.println("Received Reply from FTP Connection:" + reply);

                            if (FTPReply.isPositiveCompletion(reply))
                            {
                                System.out.println("Connected Success");
                            }

                            in = new FileInputStream(temp);

                            ftp.storeFile(fileName, in);
                            pathHighRes = "ftp://photoviewer@" + hostName + ":" + port + "/" + fileName;

                            System.out.println("SUCCESS");

                            ftp.logout();
                        }
                        ftp.disconnect();
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                    // Low res upload
                    try
                    {
                        //creation

                        BufferedImage img = ImageIO.read(temp);

                        int original_width = img.getWidth();
                        int original_height = img.getHeight();
                        int bound_width = 200;
                        int bound_height = 200;
                        int new_width = original_width;
                        int new_height = original_height;

                        // first check if we need to scale width
                        if (original_width > bound_width)
                        {
                            //scale width to fit
                            new_width = bound_width;
                            //scale height to maintain aspect ratio
                            new_height = (new_width * original_height) / original_width;
                        }

                        // then check if we need to scale even with the new height
                        if (new_height > bound_height)
                        {
                            //scale height to fit instead
                            new_height = bound_height;
                            //scale width to maintain aspect ratio
                            new_width = (new_height * original_width) / original_height;
                        }

                        Image tmp = img.getScaledInstance(new_width, new_height, Image.SCALE_SMOOTH);
                        BufferedImage dimg = new BufferedImage(new_width, new_height, BufferedImage.SCALE_SMOOTH);
                        Graphics2D g2d = dimg.createGraphics();
                        g2d.drawImage(tmp, 0, 0, null);
                        g2d.dispose();

                        //upload
                        ServletContext servletContext = getServletContext();
                        String contextPath = servletContext.getRealPath(File.separator);
                        File lowResFile = new File(contextPath + "\\lowRes\\" + fileName + ".jpg");
                        lowResFile.getParentFile().mkdirs();
                        ImageIO.write(dimg, "JPG", lowResFile);

                        pathLowRes = "\\WEB-INF\\lowRes\\" + fileName + ".jpg";
                    } catch (Exception ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                }

                // Database
                if (fileName != null && price != null && pathLowRes != null && pathHighRes != null && groupID > -1)
                {
                    ResultSet rs = Database.getDatabase().query("INSERT INTO photo (name,price,pathlowres,pathhighres) VALUES (" + fileName + " , " + price + " , " + pathLowRes + " , " + pathHighRes + ")", Database.QUERY.UPDATE);
                    int key = -1;

                    try
                    {
                        if (rs != null)
                        {
                            while (rs.next())
                            {
                                key = rs.getInt(1);
                            }
                        }
                        if (key != -1)
                        {
                            Database.getDatabase().query("INSERT INTO photo_group (photoID,groupID) VALUES (" + key + " , " + groupID + ")", Database.QUERY.UPDATE);
                        }
                    } catch (SQLException ex)
                    {
                        Logger.getLogger(LanguageServlet.class.getName()).log(Level.SEVERE, null, ex);
                    } finally
                    {
                        Database.getDatabase().closeConnection();
                    }
                }
                

                if (!temp.delete())
                {
                    System.err.println("Temp not deleted");
                    temp.deleteOnExit();
                }
                response.sendRedirect(request.getContextPath() + "/fotograafpanel.jsp");
            } else
            {
                request.setAttribute("errorMessage", "Some fields are empty.");
                request.getRequestDispatcher("/fotograafpanel.jsp").forward(request, response);
            }
        } catch (FileUploadException e)
        {
            System.out.println("Exception in uploading file.");
        } catch (Exception e)
        {
            System.out.println("Exception in uploading file.");
        }
    }
}
