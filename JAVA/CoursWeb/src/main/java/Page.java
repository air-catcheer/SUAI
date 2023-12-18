import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Page extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<html><head><link rel=\"StyleSheet\"  type=\"text/css\"></head><body>");
        HttpSession session = request.getSession(false);
        if(session != null)
        {
            request.getRequestDispatcher("grades.html").include(request,response);
            String name = (String)session.getAttribute("name");
            out.print("welcom " + name);
            printAll(out);
        }
        else
        {
            request.getRequestDispatcher("linkin.html").include(request,response);
            out.print("welcom");
            printAll(out);
        }
        out.println("</body></html>");
        out.close();


    }
    private void printAll(PrintWriter out)throws IOException
    {
        //напиши вывод электронной ведомости из grades.txt
        String s;
        String []str;
        try {
            BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\CoursWeb\\src\\main\\resources\\grades.txt"));
            while ((s = in.readLine()) !=null) {
                s.trim();
                str = s.split(" ");
                out.println("<p>" + str[0] + " " + str[1] + " " + str[2] + "</p>");
            }
        }
        catch (Exception e){}
    }
}
