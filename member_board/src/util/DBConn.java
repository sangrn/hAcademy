package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "board", "1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

//human.lepelaka.net
	public static void main(String[] args) {
		System.out.println(getConnection());
	}
}