package usc.covider.cs310;

import org.junit.Test;

import usc.covider.cs310.util.AccountUtil;

import static org.junit.Assert.*;

public class AccountUtilTest {
    @Test
    public void accountUtilTest(){
        String password = "q";
        String password2 = "z";
        String password3 = "q";

        assertEquals(AccountUtil.checkPassword(password, password2), AccountUtil.PASSWORDS_NOT_MATCHING);
        assertEquals(AccountUtil.checkPassword(password, password3), "");
    }
}
