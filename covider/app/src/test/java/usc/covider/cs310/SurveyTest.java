package usc.covider.cs310;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SurveyTest {
    @Test
    public void testSurvey(){
        List<String> buildings = new ArrayList<>();
        Survey s = new Survey(true, false, true, 5689, "11213", buildings);

        assertEquals(s.hasCovid, true);
        assertEquals(s.hasSymptom, false);
        assertEquals(s.hasContact, true);
        assertEquals(s.date, 5689);
        assertEquals(s.userID, "11213");
    }
}
