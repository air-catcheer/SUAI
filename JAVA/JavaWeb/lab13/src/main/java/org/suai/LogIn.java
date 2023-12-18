package org.suai;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@WebServlet(name = "LogIn", urlPatterns = "/")
class LogIn extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String html = Files.lines(Paths.get("content/login.html")).collect(Collectors.joining());
        writer.println(html);
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        String html = Files.lines(Paths.get("content/login.html")).collect(Collectors.joining());
        if (login.equals("admin") && pass.equals("admin")) {
            resp.sendRedirect("/welcome");
        } else {
            resp.sendRedirect("/login");
        }
    }
}