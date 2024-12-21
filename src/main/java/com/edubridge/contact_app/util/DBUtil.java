package com.edubridge.contact_app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// singleton class
public class DBUtil {
	private static Connection con = null;

	private DBUtil() {
	}

	public static Connection getConnection() {
		if (con == null) {
			String driver, url, user, password;
			driver = "com.mysql.cj.jdbc.Driver";
			url = "jdbc:mysql://localhost:3306/edubridge";
			user = password = "root";

			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				System.out.println("No driver found");
			}

			try {
				con = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				System.out.println("Database connection failed");
			}
		}
		return con;
	}
	
	public static void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
