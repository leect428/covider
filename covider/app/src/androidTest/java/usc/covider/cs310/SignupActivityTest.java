package usc.covider.cs310;

import androidx.test.espresso.ViewAssertion;
import androidx.test.rule.ActivityTestRule;

import com.example.cs310.R;
import com.google.ar.core.Config;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SignupActivityTest {
    @Rule
    public ActivityTestRule<SignupActivity> mActivityRule =
            new ActivityTestRule<>(SignupActivity.class);

    //test successful signup
    @Test
    public void testSuccessfulSignup() {
        // Type text and then press the button.
        String possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder builder = new StringBuilder();
        Random rnd = new Random();
        while (builder.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * possible.length());
            builder.append(possible.charAt(index));
        }
        String name = builder.toString()+"@gmail.com";
        onView(withId(R.id.email)).perform(typeText(name), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.confirmpass)).perform(typeText("test"), closeSoftKeyboard());

        onView(withId(R.id.signup)).perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.fertitta_hall)).check(matches(isDisplayed()));
    }

    //fail if passwords do not match
    @Test
    public void testFailedSignup1() {
        // Type text and then press the button.
        onView(withId(R.id.email)).perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("bob"), closeSoftKeyboard());
        onView(withId(R.id.confirmpass)).perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.signup)).perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.error_signup)).check(matches(isDisplayed()));
    }

    //fail if user exists
    @Test
    public void testFailedSignup2() {
        // Type text and then press the button.
        onView(withId(R.id.email)).perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.confirmpass)).perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.signup)).perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.error_signup)).check(matches(isDisplayed()));
    }
}