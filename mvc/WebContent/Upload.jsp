<html>
<head></head>
<body>
<form role="form" action="UploadDownloadFileServlet" method="post" enctype="multipart/form-data">
Select File to Upload:<input type="file" name="fileName" multiple="">
<br>
groupID:<input type="text" class="form-control" name="tbpersoonsid" />(laat leeg indien het gaat om een inviduele foto)
<br>
Prijs:<input type="text" class="form-control" name="tbprijs" />
<br>
<input type="submit" value="Upload">
</form>

</body>

</html>