<%-- 
    Document   : CreatePost2
    Created on : Jun 20, 2024, 11:48:32 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="assets/css/testcss5.css">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <form action="imageUpload" method="post" enctype="multipart/form-data">
            <div class="form-group" style="margin: auto;width: 50%; border: 3px solid black;padding: 10px;" >
             <h1>Choose image for post</h1>
             <input type="file" class="upload-input" name="image" id="someId">
            <label for="someId" class="custom-file-upload">Upload Image</label>      
            <span id="fileName" class="file-name"></span><br>
            <p> you can skip this step </p>

            <button type="submit" >Done</button>

        </div>    
            </form>
        
        
<script>
    var file = document.getElementById('someId');
    file.onchange = function (e) {
        var ext = this.value.match(/\.([^\.]+)$/)[1];
        switch (ext) {
            case 'jpg':                 
                document.getElementById('someId').addEventListener('change', function(event) {
                const fileName = this.files[0] ? this.files[0].name : '';
                document.getElementById('fileName').textContent = fileName;
                });
                break;
            case 'jpeg':
                 document.getElementById('someId').addEventListener('change', function(event) {
                const fileName = this.files[0] ? this.files[0].name : '';
                document.getElementById('fileName').textContent = fileName;
                });
                break;
            case 'bmp':
                 document.getElementById('someId').addEventListener('change', function(event) {
                const fileName = this.files[0] ? this.files[0].name : '';
                document.getElementById('fileName').textContent = fileName;
                });
                break;
            case 'png':
                 document.getElementById('someId').addEventListener('change', function(event) {
                const fileName = this.files[0] ? this.files[0].name : '';
                document.getElementById('fileName').textContent = fileName;
                });
                break;
            case 'tif':
                 document.getElementById('someId').addEventListener('change', function(event) {
                const fileName = this.files[0] ? this.files[0].name : '';
                document.getElementById('fileName').textContent = fileName;
                });
                break;
            default:
                alert('Not allowed');
                this.value = '';
        }
    };
</script>
    </body>
</html>
