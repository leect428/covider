package usc.covider.cs310.util;

public class AccountUtil {
    public static String PASSWORDS_NOT_MATCHING = "Passwords do not match";
    public static String EMAIL_TAKEN = "Email already exists!";

    public static String checkPassword(String password, String confirmPassword){
        if(password.equals(confirmPassword)){
            return "";
        } else{
            return PASSWORDS_NOT_MATCHING;
        }
    }
}
