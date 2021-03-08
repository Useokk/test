package db;

import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JdbcUtil {
	
	public static Connection getConnection() {
		Connection conn=null;
		
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)envCtx.lookup("jdbc/OracleDB");
			conn = ds.getConnection();
			conn.setAutoCommit(false);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	public static Connection getConnection1() {
		Connection conn=null;
		
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)envCtx.lookup("jdbc/MySQLDB");
			conn = ds.getConnection();
			conn.setAutoCommit(false);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	public static void close(Connection conn){
		
		try {
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		try {
			stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		
		try {
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void commit(Connection conn) {
		try {
			conn.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			conn.rollback();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}

