package com.gzu.jdbc;

import java.sql.*;

public class Bath_Insertion {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc_demo?serverTimezone=GMT&characterEncoding=UTF-8";
        String user = "root";
        String password = "187509abMJ!#*#*";
        String sql = "INSERT INTO teacher (id, name, course, birthday) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false); // 禁用自动提交，手动控制提交

            for (int i = 1; i <= 500; i++) {
                ps.setInt(1, i);
                ps.setString(2, "Teacher" + i);
                ps.setString(3, "Course" + (i % 10)); // 分配课程名称
                ps.setDate(4, java.sql.Date.valueOf("1980-01-01")); // 生日为固定值

                ps.addBatch(); // 添加到批处理

                // 每插入100条数据提交一次
                if (i % 100 == 0) {
                    ps.executeBatch(); // 执行批处理
                    conn.commit();     // 提交事务
                    System.out.println("已提交 " + i + " 条记录");
                }
            }

            // 提交最后一批少于100条的记录
            ps.executeBatch();
            conn.commit();
            System.out.println("插入完成，总共插入了500条记录");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
