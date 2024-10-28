package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/logs")
public class LogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        ServletContext context = getServletContext();
        List<String> logs = (List<String>) context.getAttribute("logs");

        out.println("<html><head><title>Request Logs</title></head><body>");
        out.println("<h1>Request Logs</h1>");
        if (logs != null && !logs.isEmpty()) {
            out.println("<ul>");
            for (String log : logs) {
                out.println("<li>" + log + "</li>");
            }
            out.println("</ul>");
        } else {
            out.println("<p>No logs available.</p>");
        }
        out.println("</body></html>");
    }
}
