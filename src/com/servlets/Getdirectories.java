package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Getdirectories
 */
public class Getdirectories extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Getdirectories() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("yo");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("GetDirectories called");
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");
		String username = request.getParameter("username");
		ArrayList<ArrayList<String>> tempList = (ArrayList<ArrayList<String>>)session.getAttribute("UserBotInfo");
		PrintWriter out = response.getWriter();
		if(username.equals((String)session.getAttribute("User"))){
			String rootDir = null;
			for(ArrayList<String> temp : tempList){
				if(temp.get(5).equals("")){
					rootDir = temp.get(2);
					break;
				}
			}
			out.print("<option value='" + rootDir + "'>" + rootDir + "</option>");		
		}
	}

}
