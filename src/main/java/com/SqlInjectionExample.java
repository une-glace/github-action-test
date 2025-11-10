package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlInjectionExample {

    public void doLogin(String user, String pass) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/test?" + "user=myuser&password=mypass");

            // 3. 使用带有 '?' 占位符的安全 SQL 查询模板
            String query = "SELECT * FROM users WHERE user = ? AND password = ?";

            // 4. 创建 PreparedStatement 对象
            pstmt = conn.prepareStatement(query);

            // 5. 安全地设置参数值
            pstmt.setString(1, user); // 第一个 '?' 对应 user
            pstmt.setString(2, pass); // 第二个 '?' 对应 pass

            // 6. 执行查询，无需再传入 query 字符串
            pstmt.executeQuery();
            // ...

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    
//    public void doLogin(String user, String pass) {
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//            conn = DriverManager.getConnection("jdbc:mysql://localhost/test?" + "user=myuser&password=mypass");
//
//            // Safe SQL query using prepared statements.
//            String query = "SELECT * FROM users WHERE user = ? AND password = ?";
//
//            pstmt = conn.prepareStatement(query);
//            pstmt.setString(1, user);
//            pstmt.setString(2, pass);
//
//            rs = pstmt.executeQuery();
//
//            // ...
//
//        } catch (SQLException se) {
//            // Handle errors for JDBC
//            se.printStackTrace();
//        } finally {
//            // Finally block used to close resources
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (pstmt != null) {
//                    pstmt.close();
//                }
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
//        }
//    }
}
