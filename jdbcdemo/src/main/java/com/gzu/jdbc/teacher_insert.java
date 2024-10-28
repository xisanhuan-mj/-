package com.gzu.jdbc;

import java.sql.*;
import java.util.Scanner;

public class teacher_insert {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc_demo?serverTimezone=GMT&characterEncoding=UTF-8";
        String user = "root";
        String password = "187509abMJ!#*#*";
        String sql = "INSERT INTO teacher (id, name, course, birthday) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false);
            Scanner scanner = new Scanner(System.in);

            System.out.println("请输入id：");
            int id = scanner.nextInt();
            scanner.nextLine(); // 清除换行符

            System.out.println("请输入姓名：");
            String name = scanner.nextLine();

            System.out.println("请输入课程：");
            String course = scanner.nextLine();

            System.out.println("请输入生日(格式：YYYY-MM-DD)：");
            String birthdayInput = scanner.next();
            java.sql.Date birthday = java.sql.Date.valueOf(birthdayInput);

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.setString(2, name);
                ps.setString(3, course);
                ps.setDate(4, birthday);

                int rowsAffected = ps.executeUpdate();
                System.out.println("插入成功，影响的行数: " + rowsAffected);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
