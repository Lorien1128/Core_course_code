package com.inter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {
    private static final String url;
    private static final String username;
    private static final String password;

    static {
        try {
            url = "jdbc:mysql://localhost:3306/db2022?useSSL=false" +
                    "&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            username = "root";
            password = "5.19yousa";
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);

        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username,password);
    }

    public static void release(Connection conn,Statement st,ResultSet rs,PreparedStatement pst) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (pst != null) {
            try {
                pst.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
