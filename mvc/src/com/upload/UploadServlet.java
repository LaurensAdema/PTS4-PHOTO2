/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upload;

import com.database.Database;
import com.domain.site.LanguageServlet;
import com.randomcodegenerator.InlogCodeGenerator;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 *
 * @author soufyan
 */
public class UploadServlet extends HttpServlet {

    private String hostName = "84.24.156.65";
    private int port = 21;
    private String username = "photoshop";
    private String password = "pts4";
    private int maxFileSize = 5000 * 1024;
    private int maxMemSize = 5000 * 1024;

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

        int groupID = 2;
        String price = "0";
        String fileName = "DEFAULT";
        String extension = null;
        String pathLowRes = null;
        String pathHighRes = null;

        InlogCodeGenerator inlogCodeGenerator = new InlogCodeGenerator();

        String inlogcode = inlogCodeGenerator.GenerateCode();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.write("<html><head></head><body>");
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

                    if (fieldname.equals("tbGroupID"))
                    {
                        groupID = Integer.parseInt(fileItem.getString());
                    } else if (fieldname.equals("tbPrice"))
                    {
                        price = fileItem.getString();
                    }
                    fileItemsIterator1.remove();
                }
            }
            Iterator<FileItem> fileItemsIterator2 = fileItemsList.iterator();
            while (fileItemsIterator2.hasNext())
            {
                FileItem fileItem = fileItemsIterator2.next();
                fileName = fileItem.getName();
                System.out.println("FieldName=" + fileItem.getFieldName());
                System.out.println("FileName=" + fileItem.getName());
                System.out.println("ContentType=" + fileItem.getContentType());
                System.out.println("Size in bytes=" + fileItem.getSize());

                // High res temp
                System.out.println("Absolute Path at server=" + temp.getAbsolutePath());
                fileItem.write(temp);

                // out.write("File "+fileItem.getName()+ " uploaded successfully.");
                // out.write("<br>");
                //out.write("<a href=\"UploadDownloadFileServlet?fileName=High resolution\\"+fileItem.getName()+"\">Download "+fileItem.getName()+"</a>");
                // out.write("<img src=\"UploadDownloadFileServlet?fileName=High resolution\\"+fileItem.getName()+"\">Download "+fileItem.getName()+">");
                // out.write("<img src=\"UploadDownloadFileServlet?fileName=knipsel22.jpg\">Download knipsel22.jpg>");
                // High res upload
                FTPClient ftp = null;
                InputStream in = null;
                try
                {
                    ftp = new FTPClient();
                    ftp.connect(hostName, port);
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
                    BufferedImage img = ImageIO.read(temp);

                    //Image tmp = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                    BufferedImage dimg = new BufferedImage(200, 200, BufferedImage.SCALE_SMOOTH);

                    //Graphics2D g2d = dimg.createGraphics();
                    //g2d.drawImage(tmp, 0, 0, null);
                    //g2d.dispose();
                    File lowResFile = new File(request.getServletContext().getAttribute("FILES_DIR") + File.separator + "\\Low resolution\\" + FilenameUtils.removeExtension(fileName) + ".jpg");
                    lowResFile.getParentFile().mkdirs();
                    ImageIO.write(dimg, "JPG", lowResFile);

                    pathLowRes = lowResFile.getAbsolutePath();
                } catch (Exception ex)
                {
                    System.out.println(ex.getMessage());
                }
            }

            // Database
            if (fileName != null && price != null && pathLowRes != null && pathHighRes != null)
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

            //Database.getDatabase().query("INSERT INTO photo (accountID,name,price,pathlowres,pathhighres) VALUES ( 1 , test , 2 , /tesadtdsf , /teadstsdf)", Database.QUERY.UPDATE);
            //   out.write("INSERT INTO photo (accountID,name,price,pathlowres,pathhighres) VALUES ( "+Accountid+" , "+Filename+" , "+price+" , Low resolution\\"+Filename+" , High resolution\\"+Filename+")");
            out.write("Geef volgende code aan klant mee, zodat zei de relaterende fotos kunnen koppelen aan hen account");
            out.write("<br>");
            out.write(inlogcode);
            out.write("<br>");
            out.write("<a href=\"/WEB-INF/index.jsp\">Terug naar home</a>");
            out.write("</body></html>");

            //response.sendRedirect(request.getContextPath() + "/index.jsp");
        } catch (FileUploadException e)
        {
            out.write("Exception in uploading file.");
        } catch (Exception e)
        {
            out.write("Exception in uploading file.");
        }
    }
}
