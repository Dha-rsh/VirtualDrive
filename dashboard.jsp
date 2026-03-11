<%@ page import="java.util.List" %>
<%@ page import="com.virtualdrive.model.*" %>

<html>

<head>
<title>virtual drive</title>

<style>

body{
    font-family: Arial, Helvetica, sans-serif;
    background-color:#f5f7fb;
    margin:0;
    padding:20px;
}

a{
    text-decoration:none;
    color:#333;
}

h3{
    margin-top:30px;
    color:#444;
}

form{
    margin:15px 0;
}

input[type="text"], input[type="file"]{
    padding:8px;
    border:1px solid #ccc;
    border-radius:5px;
}

button{
    padding:8px 14px;
    border:none;
    background:#4285f4;
    color:white;
    border-radius:5px;
    cursor:pointer;
}

button:hover{
    background:#2f6fe4;
}

li{
    list-style:none;
    background:white;
    margin:8px 0;
    padding:10px;
    border-radius:8px;
    display:flex;
    justify-content:space-between;
    align-items:center;
    box-shadow:0 2px 5px rgba(0,0,0,0.08);
}

li a{
    margin-right:10px;
}

/* File row style */
.file-row{
    background:white;
    margin:8px 0;
    padding:10px;
    border-radius:8px;
    display:flex;
    align-items:center;
    justify-content:space-between;
    box-shadow:0 2px 5px rgba(0,0,0,0.08);
}

/* Breadcrumb */
.breadcrumb{
    background:white;
    padding:10px 15px;
    border-radius:8px;
    margin-bottom:20px;
    box-shadow:0 2px 5px rgba(0,0,0,0.1);
}

.breadcrumb a{
    text-decoration:none;
    color:#4285f4;
    font-weight:500;
}

.breadcrumb a:hover{
    text-decoration:underline;
}

i{
    margin-right:8px;
}

.fa-trash{
    color:red;
}

.fa-download{
    color:#444;
}

</style>

<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

</head>

<body>

<a href="folder?parentId=0">Home</a>

<form action="folder" method="post">

<input type="text" name="name" placeholder="Folder name">

<input type="hidden" name="parentId" value="${cpId}">

<button>Create</button>

</form>

<p>Current Folder ID : ${cpId}</p>

<h3>Upload Files</h3>

<form action="upload" method="post" enctype="multipart/form-data">

<input type="hidden" name="folderId" value="${cpId}">

<input type="file" name="file">

<button type="submit">Upload</button>

</form>


<!-- Breadcrumb -->

<div class="breadcrumb">

<a href="folder?parentId=0">Home</a>

<%

List<Folder> breadcrumb = (List<Folder>)request.getAttribute("nav");

if(breadcrumb != null){

for(Folder b : breadcrumb){

%>

 > <a href="folder?parentId=<%=b.getId()%>">
 <%=b.getName()%>
 </a>

<%

}

}

%>

</div>


<%

List<Folder> folders=(List<Folder>)request.getAttribute("folders");

%>


<h3>Folders</h3>

<%

if(folders !=null){

for(Folder f:folders){

%>

<li>

<a href="folder?parentId=<%=f.getId()%>">

<i class="fas fa-folder" style="color:rgb(255,212,59);"></i>

<%= f.getName() %>

</a>

<a href="deleteFolder?parentId=<%=f.getId()%>">

<i class="fa-solid fa-trash"></i>

</a>

</li>

<%

}

}

%>


<h3>Files</h3>

<%

List<FileEntity> files = (List<FileEntity>)request.getAttribute("files");

if(files != null){

for(FileEntity file : files){

%>

<div class="file-row">

<span>

<i class="fas fa-file"></i>

<%=file.getName()%>

</span>

<span>

<a href="download?id=<%=file.getId()%>">

<i class="fa-solid fa-download"></i>

</a>

<a href="delete?id=<%=file.getId()%>&folderId=${cpId}">

<i class="fa-solid fa-trash"></i>

</a>

</span>

</div>

<%

}

}

%>

</body>

</html>