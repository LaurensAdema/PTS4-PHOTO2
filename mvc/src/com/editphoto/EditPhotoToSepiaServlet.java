package com.editphoto;

import com.database.Database;
import com.randomcodegenerator.CodeGenerator;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.MultipartConfig;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.File;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

public class EditPhotoToSepiaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ServletFileUpload uploader = null;

    @Override
    public void init() throws ServletException {
        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
        File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
        fileFactory.setRepository(filesDir);
        this.uploader = new ServletFileUpload(fileFactory);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("fileName");
        if (fileName == null || fileName.equals("")) {
            throw new ServletException("File Name can't be null or empty");
        }
        File file = new File(request.getServletContext().getAttribute("FILES_DIR") + File.separator + fileName);
        if (!file.exists()) {
            throw new ServletException("File doesn't exists on server.");
        }
        System.out.println("File location on server::" + file.getAbsolutePath());
        ServletContext ctx = getServletContext();
        InputStream fis = new FileInputStream(file);
        String mimeType = ctx.getMimeType(file.getAbsolutePath());
        response.setContentType(mimeType != null ? mimeType : "application/octet-stream");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        ServletOutputStream os = response.getOutputStream();
        byte[] bufferData = new byte[1024];
        int read = 0;
        while ((read = fis.read(bufferData)) != -1) {
            os.write(bufferData, 0, read);
        }
        os.flush();
        os.close();
        fis.close();
        System.out.println("File downloaded at client successfully");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new ServletException("Content type is not multipart/form-data");
        }

        
        ToSepia Sepia = new ToSepia();
        
        
        String Accountid = null;
        String Prijs = null;
        String Pathlowres = null;
        String inlogcode = CodeGenerator.generateAlphanumericCode(8);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.write("<html><head></head><body>");
        try {
            List<FileItem> fileItemsList = uploader.parseRequest(request);
            
            Iterator<FileItem> fileItemsIterator2 = fileItemsList.iterator();
          
            while (fileItemsIterator2.hasNext()) {
                FileItem fileItem = fileItemsIterator2.next();
                System.out.println("FieldName=" + fileItem.getFieldName());
                System.out.println("FileName=" + fileItem.getName());
                System.out.println("ContentType=" + fileItem.getContentType());
                System.out.println("Size in bytes=" + fileItem.getSize());

                File file = new File(request.getServletContext().getAttribute("FILES_DIR") + File.separator + "\\High resolution\\" + fileItem.getName());

                file.getParentFile().mkdirs();
                System.out.println("Absolute Path at server=" + file.getAbsolutePath());
                fileItem.write(file);
                // out.write("File "+fileItem.getName()+ " uploaded successfully.");
                // out.write("<br>");
                //out.write("<a href=\"UploadDownloadFileServlet?fileName=High resolution\\"+fileItem.getName()+"\">Download "+fileItem.getName()+"</a>");

                if (fileItem.getName() != null) {
                    
                }


                // out.write("<img src=\"UploadDownloadFileServlet?fileName=knipsel22.jpg\">Download knipsel22.jpg>");
                try {
                    BufferedImage img = ImageIO.read(file);

                    Image tmp = img.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
                   
                   
                    BufferedImage dimg = new BufferedImage(400, 400, BufferedImage.TYPE_BYTE_GRAY);
                    
                    dimg =  Sepia.toSepia(dimg, 200);

    
                    
                    
                    Graphics2D g2d = dimg.createGraphics();
              
                    g2d.drawImage(tmp, 0, 0, null);
                    g2d.dispose();
                    
                    

                    File file2 = new File(request.getServletContext().getAttribute("FILES_DIR") + File.separator + "\\Low resolution\\" + fileItem.getName());
                 // iemand heeft dit ooit gedaan(ipv van bovenste lijn)? : File file2 = new File(request.getServletContext().getAttribute("FILES_DIR") + File.separator + "\\Low resolution\\" + fileItem.getName().split(".")[0].split("\\")[1] + ".jpg");
                    file2.getParentFile().mkdirs();
                    ImageIO.write(dimg, "JPG", file2);

                   out.write("<img src=\"UploadDownloadFileServlet?fileName=Low resolution\\"+fileItem.getName()+"\">Download "+fileItem.getName()+">");
                    
           
                   
                   
                   
                    Pathlowres = file2.getAbsolutePath();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (FileUploadException e) {
            out.write("Exception in uploading file.");
        } catch (Exception e) {
            out.write("Exception in uploading file.");
        }

        //Database.getDatabase().query("INSERT INTO photo (accountID,name,price,pathlowres,pathhighres) VALUES ( 1 , test , 2 , /tesadtdsf , /teadstsdf)", Database.QUERY.UPDATE);
        //   out.write("INSERT INTO photo (accountID,name,price,pathlowres,pathhighres) VALUES ( "+Accountid+" , "+Filename+" , "+Prijs+" , Low resolution\\"+Filename+" , High resolution\\"+Filename+")");
        
        out.write("<a href=\"/WEB-INF/index.jsp\">Terug naar home</a>");
        out.write("</body></html>");

        //response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

}
