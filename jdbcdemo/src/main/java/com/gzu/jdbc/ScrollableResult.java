package com.gzu.jdbc;

import java.sql.*;

public class ScrollableResult {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc_demo?serverTimezone=GMT&characterEncoding=UTF-8";
        String user = "root";
        String password = "187509abMJ!#*#*";
        String sql = "SELECT * FROM teacher";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(
                     sql,
                     ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = ps.executeQuery()) {

            // 移动到结果集的最后一行
            if (rs.last()) {
                int totalRows = rs.getRow(); // 获取总行数

                // 检查是否至少有两行
                if (totalRows > 1) {
                    rs.absolute(totalRows - 1); // 移动到倒数第二条记录

                    // 读取并打印倒数第二条记录的信息
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String course = rs.getString("course");
                    Date birthday = rs.getDate("birthday");

                    System.out.printf("倒数第二条记录 - ID: %d, 姓名: %s, 课程: %s, 生日: %s%n",
                            id, name, course, birthday.toString());
                } else {
                    System.out.println("结果集中没有足够的数据行。");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
