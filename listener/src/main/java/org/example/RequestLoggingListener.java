package org.example;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@WebListener
public class RequestLoggingListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        ServletContext context = request.getServletContext();

        LocalDateTime startTime = LocalDateTime.now();
        request.setAttribute("startTime", startTime);

        String clientIP = request.getRemoteAddr();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        String userAgent = request.getHeader("User-Agent");

        String logMessage = String.format(
                "Request Initialized: Time=%s, Client IP=%s, Method=%s, URI=%s, QueryString=%s, User-Agent=%s",
                startTime, clientIP, method, uri, queryString, userAgent
        );

        // 获取上下文中的日志列表
        List<String> logs = (List<String>) context.getAttribute("logs");
        if (logs == null) {
            logs = new LinkedList<>();
        }
        logs.add(logMessage);
        context.setAttribute("logs", logs); // 将日志存回上下文
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        ServletContext context = request.getServletContext();

        LocalDateTime startTime = (LocalDateTime) request.getAttribute("startTime");
        LocalDateTime endTime = LocalDateTime.now();
        long processingTime = java.time.Duration.between(startTime, endTime).toMillis();

        String logMessage = String.format(
                "Request Completed: StartTime=%s, EndTime=%s, ProcessingTime=%d ms",
                startTime, endTime, processingTime
        );

        // 获取上下文中的日志列表
        List<String> logs = (List<String>) context.getAttribute("logs");
        if (logs == null) {
            logs = new LinkedList<>();
        }
        logs.add(logMessage);
        context.setAttribute("logs", logs); // 将日志存回上下文
    }
}
