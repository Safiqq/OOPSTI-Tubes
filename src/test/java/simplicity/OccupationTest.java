package simplicity;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OccupationTest {
    private static Occupation occupation;

    @BeforeClass
    public static void setUp() {
        Occupation.fillListJob();
        occupation = new Occupation();
    }

    @Test
    public void testOccupationConstructor() {
        String job = occupation.getJobName();
        if (Main.equals(job, "Badut Sulap")) {
            assertEquals("salary not equal", occupation.getDailySalary(), 15);
        } else if (Main.equals(job, "Koki")) {
            assertEquals("salary not equal", occupation.getDailySalary(), 30);
        } else if (Main.equals(job, "Polisi")) {
            assertEquals("salary not equal", occupation.getDailySalary(), 35);
        } else if (Main.equals(job, "Programmer")) {
            assertEquals("salary not equal", occupation.getDailySalary(), 45);
        } else if (Main.equals(job, "Dokter")) {
            assertEquals("salary not equal", occupation.getDailySalary(), 50);
        } else if (Main.equals(job, "Ojek Payung")) {
            assertEquals("salary not equal", occupation.getDailySalary(), 1);
        } else if (Main.equals(job, "Pawang Hujan")) {
            assertEquals("salary not equal", occupation.getDailySalary(), 5);
        } else if (Main.equals(job, "Pelukis")) {
            assertEquals("salary not equal", occupation.getDailySalary(), 20);
        } else if (Main.equals(job, "Tukang Parkir")) {
            assertEquals("salary not equal", occupation.getDailySalary(), 2);
        } else if (Main.equals(job, "Pengusaha")) {
            assertEquals("salary not equal", occupation.getDailySalary(), 60);
        }
    }

    @Test
    public void testOccupationSet() {
        occupation.setJobName("Gubernur Lampung");
        assertEquals("Gubernur Lampung", occupation.getJobName());
    }
}
