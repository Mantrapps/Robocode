

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JBDCInsertFile {


	public static void main(String[] args) throws FileNotFoundException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		String url = "jdbc:sqlserver://cloudfall16.database.windows.net:1433;database=DbRobo;user=RobocodeAdmin@cloudfall16;password=admin@robocode123;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
		String user = "RobocodeAdmin@cloudfall16";
		String password = "admin@robocode123";

		String filePath2 = "C:/robocode/robots/sample/Interactive.java";

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			Connection conn = DriverManager.getConnection(url);

		String sql="ALTER TABLE db0.robot CHANGE file1 filedata LONGBLOB";
			//String sql = "UPDATE robot SET file=? where robotID='Interactive'";
			//String sql2="DROP TABLE robot";
			PreparedStatement statement = conn.prepareStatement(sql);
			File file=new File(filePath2);
			InputStream inputStream = new FileInputStream(file);
			//statement.setBinaryStream(1,inputStream,(int)file.length());
			

			int row = statement.executeUpdate();
			if (row > 0) {
				System.out.println("A contact was inserted with photo image.");
			}
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}