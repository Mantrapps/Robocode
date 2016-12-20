<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Add New Directory</h1>

 <form>
  Add URL of Parent Directory<br>
  <input type="text" name="Parent"><br>
  Name of the new Directory<br>
  <input type="text" name="NewDir">
</form> 

<button onclick="myFunction()">Update New Directory</button>
<script>
function myFunction() {
	String dir = request.getParameter("Parent");
	String newdir = request.getParameter("NewDir");
	String temp = dir + "/" + newdir + "/";
		try {
			//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			//String connectionUrl = "jdbc:sqlserver://robocode.database.windows.net:1433;database=robocodedbb1;user=robocode@robo;password=robo.123;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

			//Connection connection = DriverManager.getConnection(connectionUrl,"username","pwd");
			String connectionURL = "jdbc:sqlserver://cloudfall16.database.windows.net:1433;database=DbRobo;user=RobocodeAdmin@cloudfall16;password=admin@robocode123;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			Connection connection = DriverManager.getConnection(connectionURL);/*"robo",
					"robo.123");*/
			Statement statement = connection.createStatement();					
			String selectString= ("INSERT into [dbo].[DirectoryTab]([DirName],[UserId],[hierId]) VALUES ('"+newdir+"',1,'"+temp+"')");
			
</script>
</body>
</html>