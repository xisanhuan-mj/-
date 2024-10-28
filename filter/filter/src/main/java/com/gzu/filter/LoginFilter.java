package com.gzu.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter("/*")
public class LoginFilter implements Filter {

    // 不需要登录的路径
    private static final List<String> EXCLUDE_PATHS = Arrays.asList("/login", "/login.html", "/register", "/public");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化方法，如果需要可以在此配置
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 获取请求路径
        String path = httpRequest.getRequestURI();

        // 如果请求路径在排除列表中，允许通过
        if (isExcludedPath(path)) {
            chain.doFilter(request, response);
            return;
        }

        // 检查用户是否已登录
        HttpSession session = httpRequest.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            // 用户已登录，允许继续
            chain.doFilter(request, response);
        } else {
            // 用户未登录，重定向到登录页面
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.html");
        }
    }

    @Override
    public void destroy() {
        // 清理工作
    }

    // 检查当前请求路径是否在排除列表中
    private boolean isExcludedPath(String path) {
        return EXCLUDE_PATHS.stream().anyMatch(excludedPath -> path.startsWith(excludedPath));
    }
}
