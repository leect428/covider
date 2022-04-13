package usc.covider.cs310;
import org.junit.Test;

import static org.junit.Assert.*;

public class SurveyTest {
    @Test
    public void testSurvey(){
        Survey s = new Survey(true, false, true, "April 49th", "11213");

        assertEquals(s.hasCovid, true);
        assertEquals(s.hasSymptom, false);
        assertEquals(s.hasContact, true);
        assertEquals(s.date, "April 49th");
        assertEquals(s.userID, "11213");
    }
}
