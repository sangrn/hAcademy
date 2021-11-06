package util;

public class DBConnTests {
	public static void main(String[] args) {
		testGetConnection();
	}

	public static void testGetConnection() {
		System.out.println(DBConn.getConnection());
	}
}
