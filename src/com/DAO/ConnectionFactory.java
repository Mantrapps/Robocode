package com.DAO;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;

	public class ConnectionFactory {
		//String connectionUrl = "jdbc:sqlserver://groupb1.database.windows.net:1433;database=robocodedbb1;user=groupb1@groupb1;password=Qwerty1234;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";


		//String driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		//String dbUser = "groupb1";
		//String dbPwd = "Qwerty1234";
		String driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String connectionUrl = "jdbc:sqlserver://cloudfall16.database.windows.net:1433;database=DbRobo;user=RobocodeAdmin@cloudfall16;password=admin@robocode123;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
		
		String dbUser = "RobocodeAdmin@cloudfall16";
		String dbPwd = "admin@robocode123";


		private static ConnectionFactory connectionFactory = null;

		private ConnectionFactory() throws InstantiationException, IllegalAccessException {
			try {
				Class.forName(driverClassName).newInstance();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		public Connection getConnection() throws SQLException {
			Connection conn = null;
			conn = DriverManager.getConnection(connectionUrl);

			return conn;
		}

		public static ConnectionFactory getInstance() throws Exception {
			if (connectionFactory == null) {
				connectionFactory = new ConnectionFactory();
			}
			return connectionFactory;
		}
	}
