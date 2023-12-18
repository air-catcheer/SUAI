import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class Reply extends HttpServlet {


    public static void saveReply(String adId, String text) {
        AdList.Ad ad = AdList.getAdById(adId);
        assert ad != null;
        ad.addReply(text);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");
        String reply = request.getParameter("reply");
        String user_id = request.getParameter("user_id");

        saveReply(id, reply);

        out.println("<html><head><title>Reply</title></head><body>");
        out.println("<h2>Reply</h2>");
        out.println("<form action=\"mainpage\" method=\"POST\">");
        out.println("<input type=\"hidden\" name=\"user_id\" value=\"" + user_id + "\">");
        out.println("<input type=\"submit\" value=\"Back to mainpage\">");
        out.println("</form>");
        out.println("</body></html>");

        out.close();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
