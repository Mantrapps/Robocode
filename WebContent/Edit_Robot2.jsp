<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%
	ResultSet resultset = null;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Robot</title>
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
<style>
        #RobotCode { 
                position: absolute;
                top: 10px;
                right: 0;
                bottom: 0;
                left: 0;
            }
            #divdata {
        position: relative;
        top: 10px;
        }
    </style>
</head>

<body onload ="onloadPage()">
<script type="text/javascript">
function onloadPage(){
	 document.getElementById('textArea').style.display = "none";
}
</script>
<%@include file="includes/header.jsp" %>
<!--  <body id="page-top" class="index">
	<!-- /.container-fluid  </nav> -->
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<script type="text/javascript">
	<script type="text/javascript">
	function New(){
		window.open("NewRobot.jsp",null,
		"height=200,width=400,status=yes,toolbar=no,menubar=no,location=no");
 	}
	function Edit(){
		window.open("Edit_Robot.jsp",null,
		"height=200,width=400,status=yes,toolbar=no,menubar=no,location=no");
 	}
	function View(){
		window.open("ViewRobot.jsp",null,
		"height=200,width=400,status=yes,toolbar=no,menubar=no,location=no");
 	}
	</script>
	<div class="dropdown">

		<!-- trigger button -->
		<button>File</button>

		<!-- dropdown menu -->
		<ul class="dropdown-menu">
			<li onclick="New()"><a href="#open">New Robot</a></li>
			<li onclick="Edit()"><a href="#open">Edit Robot</a></li>
			<li onclick="View()"><a href="#open">View Robot</a></li>
		
		</ul>

	</div>
	<div class="dropdown">
		<button>Edit</button>
<script type="text/javascript">
	function Undo(){
		editor.session.getUndoManager().undo(false);
	}
	</script>
	<script type="text/javascript">
	function Redo(){
		editor.session.getUndoManager().redo(false);
	}
	</script>
		<!-- dropdown menu -->
		<ul class="dropdown-menu">
			<li onclick="Undo();"><a href="#Undo">Undo</a></li>
			<li onclick="Redo();"><a href="#Undo;">Redo</a></li>
			<li><a href="#Copy">Copy</a></li>
			<li><a href="#Paste">Paste</a></li>
			<li><a href="#SelectAll">Select All</a></li>
		</ul>
	</div>
	<div class="dropdown">
		<button onclick="SaveRobot2();">Save</button>
	</div>
	<script type="text/javascript">
		function getDirectories() {
			var x = document.getElementById("user").value;
			$.ajax({
				url : "GetDirectories",
				data : {username: x, mode: "write"},
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
		alert("Called");
		$.ajax({
		url : "editservlet",
		data: "domain_name="+x+"-"+"sample"+"-"+value,
		type : 'POST',
		async : false,
		success : function(html) {
			editor.getSession().setValue(html);
			//$("#RobotCode").html(html);
			console.log(html);
			}
		});
		event.preventDefault();					         					        
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
	function SaveAndCompile(){
		$.ajax({
			   url:SaveRobot2(),
			   success:function(){
			   Compile2();
			}
			});
			}
	function Compile2() {

		var code = editor.getValue();
		document.getElementById('textArea').style.display = "block";
		document.getElementById('textArea').value = code;
		document.getElementById('divdata').action = "compileservlet";
		document.getElementById("divdata").submit();

	}
		function Compile() {
			var x =editor.getValue();
			var y = document.getElementById("displayrobots").value;
			var z = document.getElementById("package").value;
			$.ajax({
				url : 'compileservlet',
				type : 'POST',
				data : "RobotCode=" + y + "blah" + z + "blah" + x,
				async : false,
				success : function(html) {
					editor.getSession().setValue(html);
					console.log(html);
					 $("#message").fadeIn(200);
	    				document.getElementById('message').innerHTML = 'Successfully compiled';
	    				setTimeout(fade_out, 5000);
				}
			});
			event.preventDefault();
		}
	</script>
	<div class="dropdown">
		<div class="dropdown">
		<button onclick="SaveAndCompile();">Compile</button>
	</div>
	</div>

	<div class="dropdown">
		<button>Help</button>
	</div>

	<!-- /.row -->
	
	<div class="row">
		<div class="col-lg-6">
		<form method="post" action="editservlet">
				<div class="form-group">
					<div class="input-group">
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
	<span id="selectArea" style></span>
						
						<br /> <br /> <br />

					</div>
				</div>
				<script type="text/javascript">
				var fade_out = function() {
					  $("#Message").fadeOut().empty();
					}
				$(document).ready(function(){
					 $("#Message").fadeIn(200);
	    				document.getElementById('Message').innerHTML = '${message}';
	    				setTimeout(fade_out, 3000);
					});
				</script>
			</form>
			<div id="Message">
			
			</div>
			  <form method="post" id="divdata" action="updateRobot">
			 <div id="RobotCode"><%String Robocode = (String) request.getAttribute("Robocode"); if(Robocode != null) {%> <%=Robocode %> <%} %></div>
    <br>
    <br>
	<textarea id="textArea" name="textArea" rows="16" cols="100"  style="display:none"></textarea>
	</form>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/ace/1.1.01/ace.js"" type="text/javascript" charset="utf-8"></script>
    <script>
        var editor = ace.edit("RobotCode");
        editor.setTheme("ace/theme/clouds");
        editor.getSession().setMode("ace/mode/java");
        var undo=new ace.UndoManager();
        editor.getSession().setUndoManager(undo);
        function SaveRobot2() {
            var code = editor.getValue();
            document.getElementById('textArea').style.display = "block";
            document.getElementById('textArea').value=code;
            document.getElementById('divdata').action = "updateRobot";
            document.getElementById("divdata").submit();
        }
        function Compile2() {
            var code = editor.getValue();
            document.getElementById('textArea').style.display = "block";
            document.getElementById('textArea').value=code;
            document.getElementById('divdata').action = "compileservlet";
            document.getElementById("divdata").submit();
        }
    </script>
			<!--  <form >
				<div class="form-group" id="RobotCodeh"
					style="position: absolute; top: 150px; left: 15px;">
					<textarea style="display: block;" name="editor" id="RobotCode"
						rows="16" cols="100">
					</textarea>
				</div>-->
				<div>
				 <br><br><br><br><br><br>  <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br> <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
				
				<!--  <button type="submit" class="btn btn-success" id="saveButton" onclick="SaveRobot();">Save</button>-->
				</div>

		</div>
		<!-- /.col-lg-6 (nested) -->
	</div>



</body>
</html>