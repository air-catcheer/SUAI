import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Page extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html><head><link rel=\"StyleSheet\" type=\"text/css\"></head><body>");

        HttpSession session = request.getSession(false);
        if (session != null) {
            request.getRequestDispatcher("link.html").include(request, response);
            String name = (String) session.getAttribute("name");
            out.print("welcome " + name);
            printAll(out, request);
        } else {
            request.getRequestDispatcher("linkin.html").include(request, response);
            out.print("welcome");
            printAll(out, request);
        }

        out.println("</body></html>");
        out.close();
    }

    private void printAll(PrintWriter out, HttpServletRequest request) throws IOException {
        String adsFileName = "/home/adduser/lab15/src/main/java/ads.txt";
        AdList ads = readAdsFromFile(adsFileName);

        for (int i = 0; i < ads.size(); i++) {
            out.println("<table border=\"2\" width=\"100%\">" +
                    "<caption><h2>" + ads.getAd(i).getHeader() + "</h2></caption>" +
                    "<tr>" +
                    "<td rowspan=\"2\">" + "<div id=\"text\">" + ads.getAd(i).getDescription() + "</div></td>" +
                    "<td width=\"25%\">Author: " + ads.getAd(i).getName() + "</td>" +
                    "<td>Date: " + ads.getAd(i).getTime() + "</td>" +
                    "</tr>" +
                    "</table>" +
                    "<div><a href=\"Reply?adId=" + i + "\">Reply</a></div><br>");

            // Display replies for the current ad
            printReplies(out, i, request);
        }
    }

    private AdList readAdsFromFile(String fileName) throws IOException {
        AdList ads = new AdList();
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            String s;
            String[] str;
            while ((s = in.readLine()) != null) {
                str = s.split(";");
                ads.add(str[0], str[1], str[2], str[3]);
            }
        }
        return ads;
    }

    private void printReplies(PrintWriter out, int adId, HttpServletRequest request) throws IOException {
        String replyFileName = "/home/adduser/lab15/src/main/java/replies.txt";
        List<String> replies = readRepliesFromFile(replyFileName);

        for (String reply : replies) {
            String[] replyInfo = reply.split(";");
            int repliedAdId = Integer.parseInt(replyInfo[3]);

            if (repliedAdId == adId) {
                out.println("<div>Reply: " + replyInfo[0] + "</div>");
                out.println("<div>Author: " + replyInfo[1] + "</div>");
                out.println("<div>Date: " + replyInfo[2] + "</div><br>");
            }
        }

        // If no replies, display a message
        if (replies.isEmpty()) {
            out.println("<div>No replies yet.</div><br>");
        }
    }

    private List<String> readRepliesFromFile(String fileName) throws IOException {
        List<String> replies = new ArrayList<>();
        try (BufferedReader replyReader = new BufferedReader(new FileReader(fileName))) {
            String reply;
            while ((reply = replyReader.readLine()) != null) {
                replies.add(reply);
            }
        }
        return replies;
    }
}
