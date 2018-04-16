package by.htp.library.dao.impl.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnectionHelper {

	private static final String DB_CONNECT_PROPERTY = "db_config";

	public DBConnectionHelper() {

	}

	public static Connection connect() {
		Connection connection = null;
		try {
			connection = connect(DB_CONNECT_PROPERTY);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static Connection connect(String fileName) throws SQLException, ClassNotFoundException {
		Connection connection = null;

		ResourceBundle rb = ResourceBundle.getBundle(fileName);
		String url = rb.getString("db.url");
		String login = rb.getString("db.login");
		String pass = rb.getString("db.pass");
		String driver = rb.getString("db.driver");
		Class.forName(driver);
		connection = DriverManager.getConnection(url, login, pass);

		return connection;
	}

	public static void disconnect(Connection connection) {

		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
