package usc.covider.cs310;

import androidx.test.espresso.ViewAssertion;
import androidx.test.rule.ActivityTestRule;

import com.example.cs310.R;
import com.google.ar.core.Config;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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
public class SurveyActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    //test successful login
    @Test
    public void testSurveyActivity() {
        // Type text and then press the button.
        onView(withId(R.id.email)).perform(typeText("test@test.com"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("test"), closeSoftKeyboard());

        onView(withId(R.id.login)).perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.has_covid)).check(matches(isDisplayed()));

        onView(withId(R.id.has_covid)).perform(click());
        onView(withId(R.id.submit)).perform(click());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.recycler)).check(matches(isDisplayed()));
    }
}