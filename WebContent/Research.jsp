<%@page import="com.Research"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/research.js"></script>

<meta charset="ISO-8859-1">
<title>Research Management</title>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Research Management</h1>

	<form id="formItem" name="formItem">
		
		Research paper Title:
		<input id="papername" name="papername" type="text" class="form-control form-control-sm"><br>
		Research paper Description :
		<input id="paperdescription" name="paperdescription" type="text" class="form-control form-control-sm"><br>
		Member paper Name:
		<input id="paperleader" name="paperleader" type="text" class="form-control form-control-sm"><br>
		Published paper Date:
		<input id="paperdate" name="paperdate" type="text" class="form-control form-control-sm"><br>
		University paper Name:
		<input id="papercampus" name="papercampus" type="text" class="form-control form-control-sm"><br>
		
		
		
		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
	</form>
	
	<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
	<br>
	<div id="divItemGrid">
	<%
	Research researchdObj = new Research(); 
	 out.print(researchdObj.readpaper()); 
	%>
	</div>
</div> </div> </div> 
	
</body>
</html>