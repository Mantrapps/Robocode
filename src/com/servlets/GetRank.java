package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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

public class GetRank extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		Statement stmt = null;
		try {
			Connection conn=ConnectionFactory.getInstance().getConnection();
			stmt = conn.createStatement();
			String Query = "SELECT A.[RobotName], sum(A.[TotalScore]) as TotalScore,  sum(A.[Firsts]) as Firsts, sum(A.[Seconds]) as Seconds, sum(A.[Thirds]) as Thirds,"
			+"(sum(A.SurvivalBonus)*(select top 1 SurvivalBonusRate from Game_Rule)+sum(A.BulletBonus)*(select top 1 BulletBonusRate from Game_Rule)+sum(A.RamBonus)*(select top 1 RamBonusRate from Game_Rule)) as WeightedTotalScore, "
			+"B.userID FROM [BattleResult] as A left join [robot] as B on concat(B.[packageID],'.',B.[robotID]) = A.RobotName "
			+" where A.Match_Time>(select top 1 Date_From from Game_Rule) and A.Match_Time<(select top 1 Date_To from Game_Rule)"
			+" group by A.RobotName, B.userID order by TotalScore desc";
			ResultSet rs = stmt.executeQuery(Query);
			out.println("<thead><tr><th>Ranking #</th><th>RobotName</th><th>Owner</th><th>TotalScore</th><th>WeightedScore</th><th><img src=\"img/Gold.png\" height=\"30\" width=\"30\"/>Firsts</th><th><img src=\"img/Silver.png\" height=\"30\" width=\"30\"/>Seconds</th><th><img src=\"img/Bronze.png\" height=\"30\" width=\"30\"/>Thirds</th></tr></thead>");
			out.println("<tbody>");
			int i=0;
			
			while(rs.next())
			{
				if (i%2==0)
				{
					out.print("<tr class=\"info\">");
				}
				else
				{
					out.print("<tr class=\"success\">");					
				}
				if(i==0){out.print("<td>"+(i+1)+"<img src=\"img/champs\" height=\"30\" width=\"30\"/></td>");}
				else{out.print("<td>"+(i+1)+"</td>");}
				
				out.print(
						//"<option value='1'>1</option>"
								"<td>"+rs.getString("RobotName")+"</td>"
						);
				out.print(
						//"<option value='1'>1</option>"
								"<td>"+rs.getString("userID")+"</td>"
						);	
				out.print(
						//"<option value='1'>1</option>"
								"<td>"+rs.getString("TotalScore")+"</td>"
						);
				out.print(
						//"<option value='1'>1</option>"
								"<td>"+rs.getString("WeightedTotalScore")+"</td>"
						);
				out.print(
						//"<option value='1'>1</option>"
								"<td>"+rs.getString("Firsts")+"</td>"
						);
				out.print(
						//"<option value='1'>1</option>"
								"<td>"+rs.getString("Seconds")+"</td>"
						);
				out.print(
						//"<option value='1'>1</option>"
								"<td>"+rs.getString("Thirds")+"</td>"
						);
				out.print("</tr>");
				i++;
			}
			out.println("</tbody>");
			
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}

