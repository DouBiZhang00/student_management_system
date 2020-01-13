package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {
	public static void main(String[] args) {
		try {
			
			
			Connection dbConn = getcon();
			Statement stmt = dbConn.createStatement();
			String s = "SELECT * FROM users";
			ResultSet rset = stmt.executeQuery(s);
			while(rset.next()) {
				System.out.println(rset.getString(1) + " " + rset.getString(2) );
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.print("SQL Server连接成功");
		}
	}
	public JDBCUtils() {
		// TODO Auto-generated constructor stub
	}
	public static Connection getcon() {
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String dbURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=data";
		String userName = "root";
		String userPwd = "123456";
		try {
			Class.forName(driverName);
			System.out.println("加载数据库成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("加载数据库失败");
		}
		Connection dbConn = null;
		try {
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.print("SQL Server连接成功");
		}
		return dbConn;
	}
	public static void  close(Connection conn,PreparedStatement pst){
        if(pst != null){
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	public static void  close(Connection conn,PreparedStatement pst,ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(pst != null){
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
