package com.gzu.jdbc;
import java.sql.*;

public class Teacher_Delete {
    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/jdbc_demo?serverTimezone=GMT&characterEncoding=UTF-8";
        String user = "root";
        String password = "187509abMJ!#*#*";
        String sql = "Delete FROM teacher WHERE name = ? ";

        try (Connection conn = DriverManager.getConnection(url, user, password);) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(sql);){
                // 设置参数
                ps.setString(1, "mj");
                // 执行删除
                ps.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }

        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
