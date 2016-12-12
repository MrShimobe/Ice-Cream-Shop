
//This class is used to access the database in other classes by making objects

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectorClass {
	Statement stmt;

	Statement stmt2;

	Statement stmt3;

	Statement stmt4;

	public ConnectorClass() {
		initializeDB();

	}

	private void initializeDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded");

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/employee", "root", "1224qwe.");
			System.out.print("Database Connectead");

			stmt = connection.createStatement();

			stmt2 = connection.createStatement();

			stmt3 = connection.createStatement();

			stmt4 = connection.createStatement();

		} catch (Exception ex) {
			ex.printStackTrace();

		}

	}
}
