package com.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.ConnectionFactory;

import DTO.DomainRobotListDTO;

/**
 * Servlet implementation class Login
 */
//@WebServlet("/SaveMatchLog")
// Kai z
public class SaveMatchLog extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		String match_result = req.getParameter("Match_Result");
		System.out.println(match_result);
		String[] match_detail=match_result.split("\\$");
		int num_of_round=Integer.parseInt(match_detail[1]);
		//Make_Match_id (row
		int matchid =Integer.parseInt(match_detail[0]); 
		//get current timestamp
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String timeStamp = dateFormat.format(date);
		//10
		//1st;sample.Walls;1127;500;100;426;74;10;18;10;0;0;
		//2nd;sample.Crazy;62;0;0;52;0;10;0;0;10;0;
		System.out.println(timeStamp);
		System.out.println(num_of_round);
		try {
			
			Connection conn=ConnectionFactory.getInstance().getConnection();
			
			for(int i=2;i<match_detail.length;i++)
			{
				String[] data_line=match_detail[i].split(";");
				String sql = "INSERT INTO BattleResult (Rounds,Match_Id,Match_Time,Rank,RobotName,TotalScore,Survival,SurvivalBonus,BulletDmg,BulletBonus,RamDmg2,RamBonus,Firsts,Seconds,Thirds)"
						+"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, num_of_round);
				statement.setInt(2, matchid);
				statement.setString(3, timeStamp);
				statement.setString(4, data_line[0]);
				statement.setString(5, data_line[1]);
				statement.setInt(6, Integer.parseInt(data_line[2]));
				statement.setInt(7, Integer.parseInt(data_line[3]));
				statement.setInt(8, Integer.parseInt(data_line[4]));
				statement.setInt(9, Integer.parseInt(data_line[5]));
				statement.setInt(10, Integer.parseInt(data_line[6]));
				statement.setInt(11, Integer.parseInt(data_line[7]));
				statement.setInt(12, Integer.parseInt(data_line[8]));
				statement.setInt(13, Integer.parseInt(data_line[9]));
				statement.setInt(14, Integer.parseInt(data_line[10]));
				statement.setInt(15, Integer.parseInt(data_line[11]));
				statement.executeUpdate();
			}
			conn.close();
			
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
		/**
		System.out.println(words[0] +" "+words[1]);
		String robotPackage =words[0];
		String name = words[1];
		String user = words[2];
		name=name.trim();
		session.setAttribute("roboName",name);
		request.setAttribute("roboName",name);
		request.setAttribute("package",robotPackage);
		session.setAttribute("package",robotPackage);
		String robotName = String.valueOf(request.getAttribute("roboName"));
		String packageName = String.valueOf(request.getAttribute("package"));
		System.out.println("roboName:"+robotName+" with package:"+packageName);
		String message = null;
		
		
		RobotDTO robotDTO = new RobotDTO();
		
		session.setAttribute("tenant_name", user);
		RobotDTO robotAccessDTO = new RobotDTO();
		robotAccessDTO.setUserId("User");
		robotAccessDTO.setRobotName(name);
		robotAccessDTO.setPackageId(robotPackage);


		//List<String> robotList = RobotDAO.getRobotList(robotAccessDTO);

		
		robotDTO.setRobotName(robotName);
		robotDTO.setPackageId(packageName);
		//robotDTO.setRobotDescription(robotDTO.getRobotName()+".java");
		robotDTO.setCreatedDate(String.valueOf(new Date()));
		
		
		String url = "jdbc:sqlserver://cloudfall16.database.windows.net:1433;database=DbRobo;user=RobocodeAdmin@cloudfall16;password=admin@robocode123;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
		String username = "RobocodeAdmin@cloudfall16";
		String password = "admin@robocode123";
		String robotCode="";
		System.out.println(robotDTO.getCreatedDate());
		try {
			//Connection conn=ConnectionFactory.getInstance().getConnection();
			Connection conn = DriverManager.getConnection(url, username, password);
			String sql = "INSERT INTO robot (CreatedDate,RobotCode,packageID,robotID,userID,filepath,dataaccess) VALUES(?, ?, ?, ?, ?, ?, ?) ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, robotDTO.getCreatedDate());
			statement.setString(4, robotName);
			statement.setString(3, packageName);
			statement.setString(5, user);
			statement.setString(2, robotCode);
			statement.setString(7, "Y");
			statement.setString(6, "/Users/Kaiz/robocode/robots/"+packageName+"/"+robotName+".java");
			int count = statement.executeUpdate();
			//Kai
			ServletContext context = session.getServletContext();
			String realContextPath = context.getRealPath(request.getContextPath()); 
			//File file = new File("C://robocode//robots//robocode//robots//"+packageName+"//"+robotName+".java");
			//write to file error
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(
					//new FileOutputStream("./robocode/robots/"+packageName+"/"+robotName+".java"), "utf-8"))) {
					new FileOutputStream("/Users/Kaiz/robocode/robots/"+packageName+"/"+robotName+".java"), "utf-8"))) {
				writer.write(robotCode);
			}
			String filePath2="/Users/Kaiz/robocode/robots/"+packageName+"/"+robotDTO.getRobotName()+".java";
			//update file in database
			sql="UPDATE robot SET [file] = ? WHERE robotID='"+robotName+"'";
			//sql="UPDATE robot SET file = load_file ('C:/robocode/robots/"+packageName+"/"+robotDTO.getRobotName()+".java') WHERE robotID='"+robotName+"'";
			statement = conn.prepareStatement(sql);
			File file=new File(filePath2);
			InputStream inputStream = new FileInputStream(file);
			statement.setBinaryStream(1,inputStream,(int)file.length());
			count = statement.executeUpdate();
			conn.close();
			

		}
		catch (Exception e) {
			 e.printStackTrace();
		}
		
		*/
		
}