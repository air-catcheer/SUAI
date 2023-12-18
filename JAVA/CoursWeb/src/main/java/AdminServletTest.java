import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AdminServletTest {

    private AdminServlet adminServlet;

    @Before
    public void setUp() {
        adminServlet = new AdminServlet();
        adminServlet.init();
    }

   @Test
    public void testAddStudent() {
        adminServlet.addStudent("NewStudent", "NewPassword");

        Person testPerson = adminServlet.findPerson("NewStudent");

        assertEquals("NewStudent", testPerson.getName());
        assertEquals("NewPassword", testPerson.getPassword());
    }


    @Test
    public void testAddGrade() {
        adminServlet.addGrade("Ivan", "NewSubject", 5);

        GradeRecord testGradeRecord = adminServlet.findGradeRecord("Ivan");

        assertEquals(5, (int) testGradeRecord.getSubjectGrades().get("NewSubject").get(0));
    }

    @Test
    public void testFindGradeRecord() {
        GradeRecord existingRecord = adminServlet.findGradeRecord("Ivan");
        assertTrue(existingRecord != null);

        GradeRecord nonExistingRecord = adminServlet.findGradeRecord("NonExistingStudent");
        assertTrue(nonExistingRecord == null);
    }
}
