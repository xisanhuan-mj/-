package com.gzu.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

//@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前用户的 session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 使会话失效
        }
        // 重定向到登录页面
        response.sendRedirect(request.getContextPath() + "/login.html");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 由于注销通常用 POST 请求，因此可重定向到 POST 方法
        response.sendRedirect(request.getContextPath() + "/logout");
    }
}
