<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*, access.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="http://code.jquery.com/jquery.min.js"></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Robot</title>
</head>

<body>

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
		if(null==session.getAttribute("UserDirInfo")){
			System.out.println("UserDirInfo does not exist");
		}
		ArrayList<ArrayList<String>> tempList = (ArrayList<ArrayList<String>>)session.getAttribute("UserDirInfo");
		ArrayList<String> userList = new ArrayList<String>();
		Set<String> tempSet = new HashSet<String>();
		for(ArrayList<String> temp : tempList){
			tempSet.add(temp.get(3));
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
	<span id="selectArea"></span>
	<h3>Robot Code</h3>
	<div class="form-group">
					<textarea disabled style="display: block;" name="editor" id="RobotCode"
						rows="16" cols="100">
					</textarea>
	</div>
</body>
</html>