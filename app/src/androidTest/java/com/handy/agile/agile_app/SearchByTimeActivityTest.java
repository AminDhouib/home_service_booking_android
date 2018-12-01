package com.handy.agile.agile_app;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;

import com.handy.agile.agile_app.HomeOwnerAccountActivities.SearchByTimeActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SearchByTimeActivityTest {

    @Rule
    public ActivityTestRule<SearchByTimeActivity> myActivityTestRule = new ActivityTestRule<SearchByTimeActivity>(SearchByTimeActivity.class);
    private SearchByTimeActivity searchByTimeActivity = null;

    @Before
    public void setUp() throws Exception {
        searchByTimeActivity = myActivityTestRule.getActivity();
    }

    @Test
    public void nothingEntered() {
        ViewInteraction editText = onView(withId(R.id.timeSearchEditText)).perform(typeText(""),closeSoftKeyboard());
        onView(withId(R.id.searchBtn)).perform(click());
        editText.check(matches(hasErrorText("Please enter a valid week day")));
    }

    @Test
    public void numberEnteredOne() {
        ViewInteraction editText = onView(withId(R.id.timeSearchEditText)).perform(typeText("Monday1"),closeSoftKeyboard());
        onView(withId(R.id.searchBtn)).perform(click());
        editText.check(matches(hasErrorText("Please enter a valid week day")));
    }

    @Test
    public void numberEnteredOnly() {
        ViewInteraction editText = onView(withId(R.id.timeSearchEditText)).perform(typeText("1"),closeSoftKeyboard());
        onView(withId(R.id.searchBtn)).perform(click());
        editText.check(matches(hasErrorText("Please enter a valid week day")));
    }

    @Test
    public void notADay() {
        ViewInteraction editText = onView(withId(R.id.timeSearchEditText)).perform(typeText("ThisIsNotADay"),closeSoftKeyboard());
        onView(withId(R.id.searchBtn)).perform(click());
        editText.check(matches(hasErrorText("Please enter a valid week day")));
    }

    @Test
    public void numberEnteredTwo() {
        ViewInteraction editText = onView(withId(R.id.timeSearchEditText)).perform(typeText("Tuesday2"),closeSoftKeyboard());
        onView(withId(R.id.searchBtn)).perform(click());
        editText.check(matches(hasErrorText("Please enter a valid week day")));
    }


}
