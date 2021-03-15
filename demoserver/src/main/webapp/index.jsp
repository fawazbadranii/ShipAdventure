<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP -Upload File</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>

<h2>
    File Upload
</h2>
Select a file to upload: <br />
<form action = "ShipController" method="post"
      enctype = "multipart/form-data">
    <input type = "file" name = "fileName" size = "50"  required>
    <label name="username"> <strong>${errorFile} </strong></label>
    <br />
    <input type = "submit" value = "Upload" />
</form>


</body>
</html>