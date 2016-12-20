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
//@WebServlet("/SaveRoundLog")
// Kai z
public class SaveRoundLog extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		String Round_result = req.getParameter("Round_Result");
		System.out.println(Round_result);
		String[] Round_detail=Round_result.split(";");
		
		//Make_Match_id (row
		int matchid = Integer.parseInt(Round_detail[0]); 
		//get current timestamp
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		//Date date = new Date();
		//String timeStamp = dateFormat.format(date);
		//10
		//1st;sample.Walls;1127;500;100;426;74;10;18;10;0;0;
		//2nd;sample.Crazy;62;0;0;52;0;10;0;0;10;0;
		//System.out.println(timeStamp);
		System.out.println(matchid);
		try {
			
			Connection conn=ConnectionFactory.getInstance().getConnection();
			
			String sql = "INSERT INTO BattleRoundLog (Match_Id,Round,RobotName,Survival,SurvivalBonus,BulletDmg,BulletBonus,RamDmg2,RamBonus,RoundTotal)"
					+"VALUES(?,?,?,?,?,?,?,?,?,?) ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, matchid);
			statement.setInt(2, Integer.parseInt(Round_detail[1]));
			statement.setString(3, Round_detail[2]);
			statement.setInt(4, (int)Double.parseDouble(Round_detail[3]));
			statement.setInt(5, (int)Double.parseDouble(Round_detail[4]));
			statement.setInt(6, (int)Double.parseDouble(Round_detail[5]));
			statement.setInt(7, (int)Double.parseDouble(Round_detail[6]));
			statement.setInt(8, (int)Double.parseDouble(Round_detail[7]));
			statement.setInt(9, (int)Double.parseDouble(Round_detail[8]));
			statement.setInt(10, (int)Double.parseDouble(Round_detail[9]));
			statement.executeUpdate();
		
			conn.close();
			
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
		
}