package com.gzu.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

//@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 验证用户名和密码（实际应用中应与数据库对比，且密码应进行加密处理）
        if ("user".equals(username) && "pass".equals(password)) {
            // 登录成功，创建会话并设置用户属性
            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            // 重定向到欢迎页面
            response.sendRedirect(request.getContextPath() + "/welcome.html");
        } else {
            // 登录失败，重定向回登录页面并传递错误参数
            response.sendRedirect(request.getContextPath() + "/login.html");
        }
    }
}
