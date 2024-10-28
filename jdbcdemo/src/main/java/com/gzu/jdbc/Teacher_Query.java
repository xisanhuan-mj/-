package com.gzu.jdbc;

import java.sql.*;

public class Teacher_Query {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc_demo?serverTimezone=GMT&characterEncoding=UTF-8";
        String user = "root";
        String password = "187509abMJ!#*#*";
        String sql = "SELECT id, name, course, birthday FROM teacher";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) { // 执行查询操作，返回结果集

            // 遍历结果集
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String course = rs.getString("course");
                Date birthday = rs.getDate("birthday");

                // 打印查询结果
                System.out.printf("ID: %d, 姓名: %s, 课程: %s, 生日: %s%n", id, name, course, birthday.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
