import org.junit.Test;

import static org.junit.Assert.*;

public class InternationalTest {

    

    @Test
    public void isStudyAbroad() {
        International student = new International(new Profile("John", "Doe", new Date("1/1/2003")), Major.CS, 12, true);
        assertEquals(5918, student.tuitionDue(12), 0.01);


    }

    @Test
    public void isNotStudyAbroad() {
        International student = new International(new Profile("John", "Doe", new Date("1/1/2003")), Major.CS, 12, false);
        assertEquals(35655, student.tuitionDue(12), 0.01);
    }
}