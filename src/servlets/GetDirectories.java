package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import access.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GetDirectories
 */
public class GetDirectories extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDirectories() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Version 5");
		Tree myTree = new Tree("D1");
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");
		String username = request.getParameter("username");
		String mode = request.getParameter("mode");
		int modeIndex = 0;
		if(mode.equals("read")){
			modeIndex = 8;
		}
		else if(mode.equals("write")){
			modeIndex = 9;
		}
		else if(mode.equals("execute")){
			modeIndex = 10;
		}
		System.out.println(mode);
		ArrayList<ArrayList<String>> tempList = (ArrayList<ArrayList<String>>)session.getAttribute("UserDirInfo");
		PrintWriter out = response.getWriter();
		if(username.equals((String)session.getAttribute("User"))){
			String rootDir = null;
			String rootDirId = null;
			for(ArrayList<String> temp : tempList){
				if(temp.get(5).equals("")){
					rootDir = temp.get(2);
					rootDirId = temp.get(1);
					break;
				}
			}
			out.println("<h3>Directory List: </h3>");
			out.println("<select multiple name='directory' id='dirSelect' onclick='getMoreDir()'>");
			out.println("<option value='" + rootDir + "'>" + rootDir + "</option>");
			out.println("</select>");
		}
		else{
				ArrayList<ArrayList<String>> tempBotList = (ArrayList<ArrayList<String>>)session.getAttribute("UserBotInfo");
				ArrayList<String> visibleBotList = new ArrayList<String>();
				out.println("<h3>Robot List: </h3>");
				out.println("<select multiple name='robots' id='robSelect' onclick='getRobotCode(this.value)'>");
				for(ArrayList<String> temp: tempBotList){
					if(temp.get(modeIndex).equals("1") && temp.get(5).equals(username)){
						out.println("<option value='" + temp.get(1) + "'>" + temp.get(1) + "</option>");
					}
				}
				out.println("</select>");
				/*ArrayList<ArrayList<String>> roleDirList = new ArrayList<ArrayList<String>>();
				for(ArrayList<String> temp: tempList){
				if(!temp.get(4).equals((String)session.getAttribute("User")) && 
						temp.get(4).equals(username)){
						roleDirList.add(temp);
					}
				}
				int minPathLength = roleDirList.get(0).get(5).length();
				for(ArrayList<String> temp: roleDirList){
					if(temp.get(5).length() < minPathLength) {
						minPathLength = temp.get(5).length();
					}
				}
				System.out.println("minPathLength = " + minPathLength);
				for(ArrayList<String> temp: roleDirList){
					System.out.println(temp.get(5));
					if(temp.get(5).length() == minPathLength){
					out.println("<option value='" + temp.get(2) + "'>" + temp.get(2) + "</option>");
					}
				}*/
		}
	}
}
