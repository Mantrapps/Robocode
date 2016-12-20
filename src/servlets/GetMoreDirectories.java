package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Servlet implementation class GetMoreDirectories
 */
public class GetMoreDirectories extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMoreDirectories() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");
		String currentDir = request.getParameter("currentDir");
		ArrayList<ArrayList<String>> tempList = (ArrayList<ArrayList<String>>)session.getAttribute("UserDirInfo");
		PrintWriter out = response.getWriter();
		out.println("<h3>Directory List: </h3>");
		out.println("<select multiple name='directory' id='dirSelect' onclick='getMoreDir()'>");
		ArrayList<ArrayList<String>> onlyUserDir = new ArrayList<ArrayList<String>>();
		for(ArrayList<String> temp : tempList){
			if(temp.get(4).equals((String)session.getAttribute("User"))){
				onlyUserDir.add(temp);
			}
		}
		int currentDirIndex = 0;
		for(int i = 0; i < onlyUserDir.size(); i++){
			currentDirIndex = i;
			if(onlyUserDir.get(i).get(2).equals(currentDir)){
				break;
			}
		}
		int currentDirLength = onlyUserDir.get(currentDirIndex).get(5).length();
		int nextDirLength = 0;
		boolean dirPresent = true;
		for(int i = currentDirIndex + 1; i < onlyUserDir.size(); i++){
			currentDirIndex = i;
			if(onlyUserDir.get(i).get(5).length() <= currentDirLength && !onlyUserDir.get(i).get(2).equals(currentDir)){
				out.println("</select>");
				dirPresent = false;
			}
			else if(onlyUserDir.get(i).get(5).length() > currentDirLength){
				nextDirLength = onlyUserDir.get(i).get(5).length();
				break;
			}
		}
		Set<String> tempSet = new HashSet<String>();
		for(int i = currentDirIndex; i < onlyUserDir.size() && dirPresent && onlyUserDir.get(i).get(5).length() >= nextDirLength; i++)
		{			if(onlyUserDir.get(i).get(5).length() == nextDirLength){
				tempSet.add(onlyUserDir.get(i).get(2));
			}
		}
		
		for(String dir : tempSet){
		out.println("<option value='" + dir + "'>" + dir + "</option>");
		}
		out.println("</select>");
		
		ArrayList<ArrayList<String>> tempBotList = (ArrayList<ArrayList<String>>)session.getAttribute("UserBotInfo");
		ArrayList<String> visibleBotList = new ArrayList<String>();
		out.println("<h3>Robot List: </h3>");
		out.println("<select multiple name='robots' id='robSelect' onclick='getRobotCode(this.value)'>");
		Set<String> tempSetBots = new HashSet<String>();
		for(ArrayList<String> temp: tempBotList){
			if(temp.get(3).equals(currentDir) && temp.get(4).equals((String)session.getAttribute("UserID"))){
				tempSetBots.add(temp.get(1));
			}
		}
		for(String bot: tempSetBots){
			out.println("<option value='" + bot + "'>" + bot + "</option>");
		}
		out.println("</select>");
	}
}
