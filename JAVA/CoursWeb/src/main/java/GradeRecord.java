import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GradeRecord {
    private String student;
    private Map<String, List<Integer>> subjectGrades;

    public GradeRecord() {
        subjectGrades = new HashMap<>();
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public Map<String, List<Integer>> getSubjectGrades() {
        return subjectGrades;
    }

    public void addSubjectGrade(String subject, List<Integer> grades) {
        subjectGrades.put(subject, grades);
    }

    public List<Integer> get(String subject) {
return subjectGrades.get(subject);

    }
}
