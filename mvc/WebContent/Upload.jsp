<%-- 
    Document   : Upload
    Created on : 21-jun-2016, 12:57:23
    Author     : soufyan
--%>

  <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
       pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>File Upload</title>
</head>
<body>
<form method="post" action="UploadServlet" enctype="multipart/form-data">
Select file to upload:
<input type="file" name="dataFile" id="fileChooser" enctype="multipart/form-data"  /><br/><br/>
<input type="submit" value="Upload" />
</form>
</body>
</html>
