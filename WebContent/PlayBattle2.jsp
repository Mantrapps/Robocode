<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Play Battle!</title>
    <%@include file="includes/header.jsp"%>
    <script src="http://code.jquery.com/jquery.min.js"></script>
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <!-- Custom CSS -->
    <link href="css/freelancer.css" rel="stylesheet"type="text/css">
    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">
    
	<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
	    <script language="javascript">
	    var matchid=Math.floor(Math.random()*10000)+1;
	    
	    function writeSummary(summary) {
	        summaryElem =
	            document.getElementById("summary");
	        summaryElem.innerHTML += "<br>";
	        summaryElem.innerHTML += summary;
	    }
	    </script>
</head>
<body>
<br><br><br><br><br><br>
  <!-- %@include  file="RoboCode-Applet/coderobo/coderobo/robocode.html" % -->


     <div id="includedContent"></div>
     <script src =
      "http://www.java.com/js/deployJava.js"></script>
    <script> 
        <!-- ... -->
        deployJava.runApplet(attributes,
            parameters, '1.7'); 
    </script> 
    <script type="text/javascript">
    function write_result_to_DB(matchResult)
    {
    	function writeResult(s) {
        	//new row
        	var arry=s.split("$");
        	for(i=1;i<arry.length;i++){
        		var arry2=arry[i].split(";");
        		summaryElem =document.getElementById("MatchResult");
        		new_row_html="<tr class=\"success\"><td>"+arry2[0]+"</td><td>"+arry2[1]+"</td><td>"+arry2[3]+"</td><td>"+arry2[4]+"</td><td>"+arry2[5]+"</td><td>"+arry2[6]+"</td><td>"+arry2[7]+"</td><td>"+arry2[8]+"</td><td>"+arry2[2]+"</td><td>"+arry2[9]+"</td><td>"+arry2[10]+"</td><td>"+arry2[11]+"</td><tr> ";
        		summaryElem.innerHTML += new_row_html;
        	}
        }
    	writeResult(matchResult);
    	$.ajax({
 	    	url: 'SaveMatchLog',
 	    	data: "Match_Result="+matchid+"$"+matchResult+"",
 	        type: 'POST',
 	        async: false,
 	        success: function() {
 	        	alert("Match Complete!");
 	        },
 	        error: function() {
 	    	  alert("failure");
			}
 	    }); 
    }
    </script>
    <script type="text/javascript">
    function writeTable(s) {
    	//new row
    	var arry=s.split(";");
    	summaryElem = document.getElementById("MatchTable");
    	new_row_html="<tr><td>"+arry[0]+"</td><td>"+arry[1]+"</td><td>"+arry[2]+"</td><td>"+arry[3]+"</td><td>"+arry[4]+"</td><td>"+arry[5]+"</td><td>"+arry[6]+"</td><td>"+arry[7]+"</td><td>"+arry[8]+"</td><tr> ";
        summaryElem.innerHTML += new_row_html;
        //add to db
        $.ajax({
 	    	url: 'SaveRoundLog',
 	    	data: "Round_Result="+matchid+";"+s+"",
 	        type: 'POST',
 	        async: false,
 	        success: function() {
 	        	//alert("Round Complete!");
 	        },
 	        error: function() {
 	    	  alert("Round failure");
			}
 	    }); 
    }
    </script>
   <!--  <noscript>A browser with JavaScript enabled is required for this page to operate properly.</noscript>
    <h1>Dynamic Tree Applet Demo</h1>
    <h2>This applet has been deployed with the applet tag <em>without</em> using JNLP</h2>-->
    <h1>Robocode Battle Arena</h1>
     <%
    String robots = request.getParameter("robots");
	out.println("Robots in Match: "+ robots);
%>
<br/>
    <applet alt = "Dynamic Tree Applet Demo" 
        code = 'robocode.Robocode'
        archive = "applet9.jar"
        width = 800,
        height = 600 >
        <param name="robots" value=<%=robots%> >
        </applet>  
    <h2>Match Result</h2>
    <table class="table table-striped table-hover" id="MatchResult">
    <tr><th>Rank</th><th>Robot Name</th> <th>survival</th><th>SurvivorBonus</th><th>BulletDamage</th><th>BulletKillBonus</th><th>RamDmg2</th><th>RamBonus</th><th>Total</th><th>Firsts</th><th>Seconds</th><th>Thirds</th></tr>
    </table>
    <h2>Match Details</h2>
    <table class="table table-striped table-hover" id="MatchTable">
    <tr><th>Round</th><th>Robot Name</th> <th>survivalScore</th><th>lastSurvivorBonus</th><th>bulletDamageScore</th><th>bulletKillBonus</th><th>rammingDamageScore</th><th>rammingKillBonus</th><th>Total</th></tr>
    </table>
    
    <p id="summary" hidden>  </p> 
</body>
</html>