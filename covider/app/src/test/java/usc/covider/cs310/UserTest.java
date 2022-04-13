package usc.covider.cs310;

import org.junit.Test;
import static org.junit.Assert.*;


public class UserTest {
    @Test
    public void testUser() {
        String name = "eric";
        String password = "bob";
        User u = new User(name, password);

        assertEquals(u.email, name);
        assertEquals(u.password, password);
    }
}
