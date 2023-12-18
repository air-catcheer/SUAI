import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AdminServlet extends HttpServlet {
    List<GradeRecord> gradebook;
    private List<Person> base;

    public void init() {
        gradebook = loadGradebook();
        base = loadAdminBase();
    }

    private List<Person> loadAdminBase() {
        List<Person> adminBase = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\CoursWeb\\src\\main\\resources\\admin.txt"))) {
            String s;
            String[] str;
            while ((s = in.readLine()) != null) {
                s.trim();
                str = s.split(" ");
                adminBase.add(new Person(str[0], str[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return adminBase;
    }

    private List<GradeRecord> loadGradebook() {
        List<GradeRecord> gradebook = new ArrayList<>();
        try (Reader in = new FileReader("C:\\Users\\User\\Desktop\\CoursWeb\\src\\main\\resources\\gradebook.csv")) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(in);

            for (CSVRecord record : records) {
                GradeRecord gradeRecord = new GradeRecord();
                gradeRecord.setStudent(record.get("Student"));
                for (String subject : record.toMap().keySet()) {
                    if (!subject.equals("Student")) {
                        List<Integer> grades = Arrays.stream(record.get(subject).split(" ")).map(Integer::parseInt).collect(Collectors.toList());
                        gradeRecord.addSubjectGrade(subject, grades);
                    }
                }
                gradebook.add(gradeRecord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gradebook;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        // Загружаем данные при каждом запросе
        loadGradebook();

        // Добавляем проверку для сохранения
        if ("true".equals(request.getParameter("save"))) {
            saveGradebookToFile();
            response.setContentType("text/plain");
            response.getWriter().write("success");
            return;
        }

        if (session == null) {
            String name = request.getParameter("nameAdmin");
            String password = request.getParameter("password");
            boolean flag = false;
            for (Person person : base) {
                if ((name.equals(person.getName())) && (password.equals(person.getPassword())))
                    flag = true;
            }
            out.println("<html><body>");
            if (flag) {
                request.getRequestDispatcher("link.html").include(request, response);
                out.println("Welcome " + name);
                session = request.getSession();
                session.setAttribute("name", name);
                session.setAttribute("nameAdmin", name);
                out.println(printGradebook());
            } else {
                out.println("Error!!!!");
                request.getRequestDispatcher("admin.html").include(request, response);
            }
        } else {
            request.getRequestDispatcher("link.html").include(request, response);

            String studentName = request.getParameter("student");
            String subject = request.getParameter("subject");
            String gradeStr = request.getParameter("grade.csv");
            String indexStr = request.getParameter("index");

            if (subject != null && gradeStr != null) {
                int grade = Integer.parseInt(gradeStr);
                int index = Integer.parseInt(indexStr);
                if (index == -1) {
                    addGrade(studentName, subject, grade);
                } else {
                    editGrade(studentName, subject, index, grade);
                }
                out.println("<p><strong>Grade updated successfully</strong></p>");
            }
            saveGradebookToFile();
            loadGradebook();

            out.println(printGradebook());
        }

        out.println("</body></html>");
        out.close();
    }

    synchronized void editGrade(String studentName, String subject, int index, int newGrade) {
        GradeRecord gradeRecord = findGradeRecord(studentName);
        if (gradeRecord != null) {
            List<Integer> grades = gradeRecord.getSubjectGrades().get(subject);
            if (grades != null && index >= 0 && index < grades.size()) {
                grades.set(index, newGrade);
            }
        }
    }

    public synchronized String printGradebook() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<p><strong>Electronic Gradebook</strong></p>");
        stringBuilder.append("<table border=\"1\" id=\"gradeTable\">");

        List<String> subjects = loadSubjectsFromFile();

        stringBuilder.append("<tr><th>Student</th>");

        for (String subject : subjects) {
            stringBuilder.append("<th>" + subject + "</th>");
        }
        stringBuilder.append("</tr>");

        for (GradeRecord gradeRecord : gradebook) {
            String studentName = gradeRecord.getStudent();
            stringBuilder.append("<tr><td>" + studentName + "</td>");

            for (String subject : subjects) {
                List<Integer> grades = gradeRecord.getSubjectGrades().getOrDefault(subject, new ArrayList<>());
                stringBuilder.append("<td contenteditable='true' onblur=\"saveCell('" + studentName + "', '" + subject + "')\">");
                stringBuilder.append(grades.stream().map(String::valueOf).collect(Collectors.joining(", ")));
                stringBuilder.append("</td>");
            }

            stringBuilder.append("</tr>");
        }

        stringBuilder.append("</table>");

        stringBuilder.append("<button onclick=\"saveGradebook()\">Save</button>");

        stringBuilder.append("<script>");
        stringBuilder.append("function saveGradebook() {");
        stringBuilder.append("  var xhttp = new XMLHttpRequest();");
        stringBuilder.append("  xhttp.onreadystatechange = function() {");
        stringBuilder.append("    if (this.readyState === 4 && this.status === 200) {");
        stringBuilder.append("      alert('Changes saved successfully.');");
        stringBuilder.append("    } else if (this.readyState === 4) {");
        stringBuilder.append("      alert('Error saving changes.');");
        stringBuilder.append("    }");
        stringBuilder.append("  };");
        stringBuilder.append("  xhttp.open('POST', 'admin', true);");
        stringBuilder.append("  xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');");
        stringBuilder.append("  xhttp.send('save=true');");
        stringBuilder.append("}");
        stringBuilder.append("</script>");

            stringBuilder.append("<div class=\"center-container\">");
            stringBuilder.append("<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>");
            stringBuilder.append("<a href=\"index.html\">Logout</a>");
            stringBuilder.append("</div>");

        return stringBuilder.toString();
    }


    private List<String> loadSubjectsFromFile() {
        List<String> subjects = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\CoursWeb\\src\\main\\resources\\gradebook.csv"))) {
            String header = in.readLine();
            subjects = Arrays.asList(header.split(","));
            subjects = subjects.subList(1, subjects.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    private synchronized void saveGradebookToFile() {
        Path filePath = Paths.get("C:\\Users\\User\\Desktop\\CoursWeb\\src\\main\\resources\\gradebook.csv");
        Path tempFilePath = Paths.get("C:\\Users\\User\\Desktop\\CoursWeb\\src\\main\\resources\\gradebook_temp.csv");

        try (Writer out = new FileWriter(tempFilePath.toFile());
             CSVPrinter csvPrinter = new CSVPrinter(out, CSVFormat.DEFAULT)) {

            List<String> headers = new ArrayList<>();
            headers.add("Student");
            headers.addAll(loadSubjectsFromFile());
            csvPrinter.printRecord(headers);

            for (GradeRecord gradeRecord : gradebook) {
                List<String> values = new ArrayList<>();
                values.add(gradeRecord.getStudent());
                for (String subject : loadSubjectsFromFile()) {
                    List<Integer> grades = gradeRecord.getSubjectGrades().getOrDefault(subject, new ArrayList<>());
                    values.add(grades.stream().map(String::valueOf).collect(Collectors.joining(" ")));
                }
                csvPrinter.printRecord(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.move(tempFilePath, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public synchronized void addGrade(String studentName, String subject, int grade) {
        GradeRecord gradeRecord = findGradeRecord(studentName);
        if (gradeRecord != null) {
            gradeRecord.addSubjectGrade(subject, Collections.singletonList(grade));
        }
    }

    GradeRecord findGradeRecord(String studentName) {
        for (GradeRecord gradeRecord : gradebook) {
            if (gradeRecord.getStudent().equals(studentName)) {
                return gradeRecord;
            }
        }
        return null;
    }

    public void addStudent(String newStudent, String newPassword) {
        base.add(new Person(newStudent, newPassword));

    }

    public Person findPerson(String newStudent) {
        for (Person person : base) {
            if (person.getName().equals(newStudent)) {
                return person;
            }
        }
        return null;
    }
}