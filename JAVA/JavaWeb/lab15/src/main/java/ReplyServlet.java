import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/Reply")
public class ReplyServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adId = request.getParameter("adId");

        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println("<html>");
        pw.println("<head>");
        pw.println("<title>Reply</title>");
        pw.println("</head>");
        pw.println("<body>");
        pw.println("<form action=\"Reply\" method=\"post\">");
        pw.println("<input type=\"hidden\" name=\"adId\" value=\"" + adId + "\">");
        pw.println("<textarea name=\"text\" cols=\"40\" rows=\"10\"></textarea>");
        pw.println("<br>");
        pw.println("<input type=\"submit\" value=\"Save\">");
        pw.println("</form>");
        pw.println("</body>");
        pw.println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adId = request.getParameter("adId");
        String text = request.getParameter("text");

        HttpSession session = request.getSession(false);
        if (session != null) {
            String userName = (String) session.getAttribute("name");

            if (userName != null) {
                AdList.Ad ad = AdList.getAdById(adId);
                assert ad != null;

                AdList.Reply reply = new AdList.Reply();
                reply.text = text;
                reply.name = userName;
                reply.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                writeReplyToFile(adId, reply);
            }
        }

        response.sendRedirect("MainPage");
    }

    private void writeReplyToFile(String adId, AdList.Reply reply) throws IOException {
        String replyFileName = "/home/adduser/lab15/src/main/java/replies.txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(replyFileName, true))) {
            writer.println(reply.text + ";" + reply.name + ";" + reply.time + ";" + adId);
        }
    }
}
