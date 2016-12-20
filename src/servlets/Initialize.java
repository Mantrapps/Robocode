package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Initialize
 */
public class Initialize extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Initialize() {
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
		try {
			HttpSession session = request.getSession();
			session.setAttribute("UserID", "1");
			session.setAttribute("User", "sxk159030@utdallas.edu");
			
			session.setAttribute("Role", "1");
			
			ResultSet UserBots = null;
			String connectionURL = "jdbc:sqlserver://cloudfall16.database.windows.net:1433;database=DbRobo;user=RobocodeAdmin@cloudfall16;password=admin@robocode123;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		    Connection connection = DriverManager.getConnection(connectionURL);
		    
			Statement statementUser = connection.createStatement();
			String Userid = String.valueOf(session.getAttribute("UserID"));
			String selectString="SELECT id, rb.DirectoryId, rb.DirName, ud.UserId, UserName, hierId, RoleId, ViewRobot, EditRobot, PlayRobot FROM DirectoryTab as rb JOIN Permission p on rb.DirectoryId = p.DirectoryId JOIN UserDetails ud on rb.UserId = ud.UserId WHERE rb.UserId='1'ORDER BY hierId ASC";
			UserBots = statementUser.executeQuery(selectString);
			
			selectString = "SELECT id, rb.DirectoryId, rb.DirName, ud.UserId, UserName, hierId, RoleId, ViewRobot, EditRobot, PlayRobot FROM DirectoryTab as rb JOIN Permission p on rb.DirectoryId = p.DirectoryId JOIN UserDetails ud on rb.UserId = ud.UserId WHERE p.RoleId='1' and rb.UserId!='1' ORDER BY UserId ASC, hierId ASC";
			ResultSet RoleBots = null;
			Statement statementRole = connection.createStatement();
			RoleBots = statementRole.executeQuery(selectString);
			
			Statement userRobots = connection.createStatement();
			selectString="SELECT  r.id,r.robotID, rb.DirectoryId, rb.DirName, ud.UserId, UserName, hierId, RoleId, ViewRobot, EditRobot, PlayRobot FROM DirectoryTab as rb JOIN Permission p on rb.DirectoryId = p.DirectoryId JOIN UserDetails ud on rb.UserId = ud.UserId JOIN Robot r on rb.DirectoryId = r.RoboDirID WHERE rb.UserId='1' ORDER BY hierId ASC";
			ResultSet UserRobotsRes = null;
			UserRobotsRes = userRobots.executeQuery(selectString);
			
			Statement roleRobots = connection.createStatement();
			selectString = "SELECT  r.id,r.robotID, rb.DirectoryId, rb.DirName, ud.UserId, UserName, hierId, RoleId, ViewRobot, EditRobot, PlayRobot FROM DirectoryTab as rb JOIN Permission p on rb.DirectoryId = p.DirectoryId JOIN UserDetails ud on rb.UserId = ud.UserId JOIN Robot r on rb.DirectoryId = r.RoboDirID WHERE p.RoleId='1' and rb.UserId!='1' ORDER BY UserId ASC, hierId ASC";
			ResultSet RoleRobotsRes = null;
			RoleRobotsRes = roleRobots.executeQuery(selectString);
			
			ArrayList<ArrayList<String>> UserDirArray = new ArrayList<ArrayList<String>>();
			while(UserBots.next()){
				System.out.print(UserBots.getString(1) + " " + UserBots.getString(2) + " " + UserBots.getString(3) + " " + UserBots.getString(4) +
						" " + UserBots.getString(5) + " " + UserBots.getString(6) + " " + UserBots.getString(7) + " " + UserBots.getString(8) + " " + UserBots.getString(9) + " " + UserBots.getString(10) + "\n");
				ArrayList<String> temp = new ArrayList<String>();
				for(int i = 1; i <= 10; i++){
					temp.add(UserBots.getString(i));
				}
				UserDirArray.add(temp);
			}
			while(RoleBots.next()){
				System.out.print(RoleBots.getString(1) + " " + RoleBots.getString(2) + " " + RoleBots.getString(3) + " " + RoleBots.getString(4) +
						" " + RoleBots.getString(5) + " " + RoleBots.getString(6) + " " + RoleBots.getString(7) + " " + RoleBots.getString(8) + " " + RoleBots.getString(9) + " " + RoleBots.getString(10) + "\n");
				ArrayList<String> temp = new ArrayList<String>();
				for(int i = 1; i <= 10; i++){
					temp.add(RoleBots.getString(i));
				}
				UserDirArray.add(temp);
			}
			for(ArrayList<String> templist: UserDirArray){
				for(String something: templist){
					System.out.print(something + " ");
				}
				System.out.print("\n");
			}
			session.setAttribute("UserDirInfo", UserDirArray);
			if(null==session.getAttribute("UserDirInfo")){
				System.out.println("UserDirInfo not set");
			}
			
			ArrayList<ArrayList<String>> UserRobotArray = new ArrayList<ArrayList<String>>();
			while(UserRobotsRes.next()) {
				ArrayList<String> temp = new ArrayList<String>();
				for(int i = 1; i <= 11; i++) {
					temp.add(UserRobotsRes.getString(i));
				}
				UserRobotArray.add(temp);
			}
			while(RoleRobotsRes.next()){
				ArrayList<String> temp = new ArrayList<String>();
				for(int i = 1; i <= 11; i++) {
					temp.add(RoleRobotsRes.getString(i));
				}
				UserRobotArray.add(temp);
			}
			session.setAttribute("UserBotInfo", UserRobotArray);
			
		} catch(Exception e)
		{
			System.out.println("Something went wrong.");
			e.printStackTrace();
		}
	}
}
