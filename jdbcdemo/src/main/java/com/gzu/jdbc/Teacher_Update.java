package com.gzu.jdbc;

import java.sql.*;
import java.util.Scanner;

public class Teacher_Update {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc_demo?serverTimezone=GMT&characterEncoding=UTF-8";
        String user = "root";
        String password = "187509abMJ!#*#*";
        // 正确的 SQL 语句
        String sql = "UPDATE teacher SET name = ?, course = ?, birthday = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false);

            Scanner scanner = new Scanner(System.in);

            // 输入信息
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

            // 预处理 SQL 语句
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                // 绑定参数
                ps.setString(1, name);
                ps.setString(2, course);
                ps.setDate(3, birthday);
                ps.setInt(4, id);

                // 执行更新
                int rowsAffected = ps.executeUpdate();
                System.out.println("更新成功，影响的行数: " + rowsAffected);
                conn.commit(); // 提交事务
            } catch (SQLException e) {
                conn.rollback(); // 回滚事务
                e.printStackTrace();
            } finally {
                conn.setAutoCommit(true); // 恢复自动提交模式
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
