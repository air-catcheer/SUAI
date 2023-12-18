import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginServlet extends HttpServlet {
    private List<Person> base;
    private List<GradeRecord> gradebook;
    private List<String> subjects;

    public void init() {
        base = new ArrayList<>();
        loadUserBase();
        loadGradebook();
    }

    private void loadUserBase() {
        try (BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\CoursWeb\\src\\main\\resources\\users.txt"))) {
            String s;
            String[] str;
            while ((s = in.readLine()) != null) {
                s.trim();
                str = s.split(" ");
                base.add(new Person(str[0], str[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadGradebook() {
        gradebook = new ArrayList<>();
        subjects = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\CoursWeb\\src\\main\\resources\\grade.csv"))) {
            String header = in.readLine();
            String[] subjectArray = header.split(",");
            subjects.addAll(Arrays.asList(subjectArray).subList(1, subjectArray.length));

            String line;
            while ((line = in.readLine()) != null) {
                String[] data = line.split(",");
                String studentName = data[0].trim();
                GradeRecord gradeRecord = new GradeRecord();
                gradeRecord.setStudent(studentName);
                for (int i = 1; i < data.length; i++) {
                    String[] gradesArray = data[i].trim().split(" ");
                    List<Integer> grades = new ArrayList<>();
                    for (String grade : gradesArray) {
                        grades.add(Integer.parseInt(grade));
                    }
                    gradeRecord.addSubjectGrade(subjects.get(i - 1), grades);
                }
                gradebook.add(gradeRecord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        init();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        boolean flag = false;

        for (Person person : base) {
            if (name.equals(person.getName()) && password.equals(person.getPassword())) {
                flag = true;

                HttpSession session = request.getSession();
                session.setAttribute("name", name);

                out.println("<html><body>");
                out.println("Welcome " + name);
                request.getRequestDispatcher("linkStud.html").include(request, response);

                out.println("<p><strong>Electronic Gradebook</strong></p>");
                out.println("<table border=\"1\">");
                out.println("<tr><th>Student</th>");

                for (String subject : subjects) {
                    out.println("<th>" + subject + "</th>");
                }

                out.println("</tr>");

                for (GradeRecord record : gradebook) {
                    out.println("<tr><td>" + record.getStudent() + "</td>");

                    for (String subject : subjects) {
                        List<Integer> grades = record.getSubjectGrades().getOrDefault(subject, new ArrayList<>());
                        out.println("<td>");
                        out.println(grades.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining(", ")));
                        out.println("</td>");
                    }

                    out.println("</tr>");
                }

                out.println("</table>");
                out.println("<div class=\"center-container\">");
                out.println("<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>");
                out.println("<a href=\"index.html\">Logout</a>");
                out.println("</div>");

                out.println("</body></html>");
                break;
            }
        }

        if (!flag) {
            out.println("<html><body>");
            out.println("Error!!!!");
            request.getRequestDispatcher("login.html").include(request, response);
            out.println("</body></html>");
        }

        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.html");
    }
}
