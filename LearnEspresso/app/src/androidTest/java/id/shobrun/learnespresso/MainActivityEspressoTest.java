package id.shobrun.learnespresso;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)
public class MainActivityEspressoTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureTextChangeWork(){
        onView(withId(R.id.edt_text)).perform(typeText("hello"),closeSoftKeyboard());
        onView(withId(R.id.button_change_text)).perform(click());
        onView(withId(R.id.text_printed_text)).check(matches(withText("hello")));
    }
    @Test
    public void ensureMoveAcitivityWithData(){
        onView(withId(R.id.edt_text)).perform(typeText("DicodingMenjadiAndroidDeveloperExpert"));
        onView(withId(R.id.button_switch_activity)).perform(click());
        onView(withId(R.id.text_prev_activity)).check(matches(withText("DicodingMenjadiAndroidDeveloperExpert")));
    }

}