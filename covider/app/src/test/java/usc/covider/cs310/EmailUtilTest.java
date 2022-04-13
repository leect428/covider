package usc.covider.cs310;

import org.junit.Test;

import usc.covider.cs310.util.EmailUtil;

import static org.junit.Assert.*;

public class EmailUtilTest {
    @Test
    public void testEmail(){
        String email1 = "bob@gmail.com";
        String email2 = "yes@yes.yay";
        String email3 = "bad";
        String email4 = "bad@sil..bad";
        String email5 = "very-bad@nott.good.";

        assertEquals(EmailUtil.isValid(email1), true);
        assertEquals(EmailUtil.isValid(email2), true);
        assertEquals(EmailUtil.isValid(email3), false);
        assertEquals(EmailUtil.isValid(email4), false);
        assertEquals(EmailUtil.isValid(email5), false);

    }
}
