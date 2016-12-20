<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Robot</title>
 
<!-- Bootstrap Core CSS - Uses Bootswatch Flatly Theme: http://bootswatch.com/flatly/ -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/freelancer.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link
	href='https://fonts.googleapis.com/css?family=Righteous|Fredoka+One'
	rel='stylesheet' type='text/css'>

<link
	href="http://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css">
</head>

<body>

<%@include file="includes/header.jsp" %>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<h1>View Robot</h1>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-6">
<script type="text/javascript">
		function getDirectories() {
			var x = document.getElementById("user").value;
			$.ajax({
				url : "GetDirectories",
				data : {username: x, mode: "read"},
				type : 'POST',
				async : false,
				success : function(html) {
					console.log("html:" + html);
					$("#selectArea").html(html);
				},
				error : function(html) {
					console.log("error html:" + html);
				}
			});
		}		
</script>
<script type="text/javascript">
		function getMoreDir() {
			var x = document.getElementById("dirSelect").value;
			$.ajax({
				url : "GetMoreDirectories",
				data : {currentDir: x},
				type : 'POST',
				async : false,
				success : function(html) {
					console.log("html:" + html);
					$("#selectArea").html(html);
				},
				error : function(html) {
					console.log("error html:" + html);
				}
			});
		}		
</script>
<script type="text/javascript">
	function getRobotCode(value)
	{				    
		var x = document.getElementById("user").value;
		$.ajax({
		url : "editservlet",
		data: "domain_name="+x+"-"+"sample"+"-"+value,
		type : 'POST',
		async : false,
		success : function(html) {
			$("#RobotCode").html(html);
			console.log(html);
			}
		});
		event.preventDefault();					         					        
	}
</script>
	<h3>Users</h3>
	<select multiple name="selectUser" id="user" onclick="getDirectories()">
		<%
		ArrayList<ArrayList<String>> tempList = (ArrayList<ArrayList<String>>)session.getAttribute("UserDirInfo");
		ArrayList<String> userList = new ArrayList<String>();
		Set<String> tempSet = new HashSet<String>();
		for(ArrayList<String> temp : tempList){
			tempSet.add(temp.get(4));
		}
		for(String user : tempSet){
			userList.add(user);
		}
		for(String user: userList){
		%>
		<option value=<%=user%>><%=user%></option>
		<%
		}
		%>
	</select>  
	
	<input type="button" value="Add New Directory" onClick="JavaScript:window.location='test.jsp';">
	
	<span id="selectArea"></span>
	<h3>Robot Code</h3>
	<div class="form-group">
					<textarea disabled style="display: block;" name="editor" id="RobotCode"
						rows="16" cols="100">
					</textarea>
	</div>
	<span id="selectArea"></span>
</body>
</html>