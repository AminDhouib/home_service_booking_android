package com.handy.agile.agile_app;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;

import com.handy.agile.agile_app.HomeOwnerAccountActivities.SearchByTypeActivity;

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

public class SearchByTypeActivityTest {

    @Rule
    public ActivityTestRule<SearchByTypeActivity> myActivityTestRule = new ActivityTestRule<SearchByTypeActivity>(SearchByTypeActivity.class);
    private SearchByTypeActivity searchByTypeActivity = null;

    @Before
    public void setUp() throws Exception {
        searchByTypeActivity = myActivityTestRule.getActivity();
    }


    @Test
    public void EmptySpaceTest() {
        ViewInteraction editText = onView(withId(R.id.whatToSearchType)).perform(typeText(""),closeSoftKeyboard());
        onView(withId(R.id.applySearchTypeButton)).perform(click());
        editText.check(matches(hasErrorText("Please enter a type!")));
    }

    @Test
    public void containsANumber() {
        ViewInteraction editText = onView(withId(R.id.whatToSearchType)).perform(typeText("painting1"),closeSoftKeyboard());
        onView(withId(R.id.applySearchTypeButton)).perform(click());
        editText.check(matches(hasErrorText("Please enter a type!")));
    }

    @Test
    public void isANumber() {
        ViewInteraction editText = onView(withId(R.id.whatToSearchType)).perform(typeText("5"),closeSoftKeyboard());
        onView(withId(R.id.applySearchTypeButton)).perform(click());
        editText.check(matches(hasErrorText("Please enter a type!")));
    }


}

