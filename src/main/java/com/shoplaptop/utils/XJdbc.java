package com.shoplaptop.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class XJdbc {
	
	private final String ServerName = "localhost";
	private final String DBName = "PM_Ban_Laptop";
	private final String PortNumber = "1433";
	private final String User = "sq";
	private final String Pass = "1234567890";
	
	String url = "jdbc:sqlserver://"+ServerName+":"+PortNumber+";databaseName="+DBName+";user="+User+";password="+Pass+";encrypt=false";
	
	public Connection Connect() {
		Connection connection = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(url);
		} catch (Exception e) {
			System.out.println("Add library jre jdbc");
		}
		return connection;
	}
	
	public static PreparedStatement getStmt(String sql, Object... args) throws SQLException {
		PreparedStatement statement ;
		if (sql.trim().startsWith("{")) {
			statement = new XJdbc().Connect().prepareCall(sql);
		}else {
			statement = new XJdbc().Connect().prepareStatement(sql);
		}
		
		for (int i = 0; i < args.length; i++) {
			statement.setObject(i+1, args[i]);
		}
		
		return statement;
	}
	
	public static int update(String sql, Object... args) throws SQLException {
		try {
			PreparedStatement statement = XJdbc.getStmt(sql, args);
			try {
				return statement.executeUpdate();
			} finally {
				statement.getConnection().close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static ResultSet query(String sql, Object... args) throws SQLException {
		PreparedStatement statement = XJdbc.getStmt(sql, args);
		return statement.executeQuery();
	}
	
	public static Object value(String sql, Object... args) throws SQLException {
		try {
			ResultSet rs = XJdbc.query(sql, args);
			if (rs.next()) {
				return rs.getObject(0);
			}
			rs.getStatement().getConnection().close();
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
