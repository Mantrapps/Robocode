<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="includes/header.jsp"%>
<script src="http://code.jquery.com/jquery.min.js"></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<!-- Custom CSS -->
<link href="css/freelancer.css" rel="stylesheet" type="text/css">

<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<link
	href="http://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css">
</head>
<body onload=" hiderank();showrank();getRank();">
<script type="text/javascript">
		
		function hiderank()
		{
			var lTable = document.getElementById("ranktable");
			lTable.style.display = "none";
		}
	</script>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div class="container">
		<div id="page-wrapper">
		<div class="row">
			<h2>Global Ranking Board</h2>
		</div>
		<script type="text/javascript">
				function getRank() {
					//var x = document.getElementById("package").value;
					x='Package1'
					$.ajax({
						url : "GetRank",
						data : "domain_name=" + x + "",
						type : 'POST',
						async : false,
						success : function(html) {
							console.log("html:" + html);
							$("#ranktable").html(html);
						},
						error : function(html) {
							console.log("error html:" + html);
						}
					});
					//alert("x value:"+x);
				}
				function showrank()
				{
					var lTable = document.getElementById("ranktable");
					lTable.style.display = "table";
				}
	</script>
	
	
	<table class="table table-striped table-hover" id="ranktable">
	<!--  
	<thead>
	<tr>
	<th>#</th>
	<th>User</th>
	<th>RobotName</th>
	<th>Score</th>
	</tr>
	</thead>
	<tbody>
	<tr>
	<td>1</td>
	<td>Column content</td>
	<td>Column content</td>
	<td>Column content</td>
	</tr>
	</tbody>-->
	</table>
	</div>
	</div>
	
	
</body>
</html>