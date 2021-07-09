package core;
import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	public static Connection con = null;

	public static void connectToDb() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			final String url = "jdbc:mysql://localhost/library_management_system";
			final String user = "root";
			final String password = "";

			con = DriverManager.getConnection(url, user, password);

			if (con == null) {
				System.out.println("connection failed");
			}
			System.out.println("connection established");

		} catch (ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class");
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
