package com.handy.agile.agile_app;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;

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

public class SignupActivityTest {

    @Rule
    public ActivityTestRule<SignUpActivity> myActivityTestRule = new ActivityTestRule<SignUpActivity>(SignUpActivity.class);
    private SignUpActivity signUpActivity = null;

    @Before
    public void setUp() throws Exception {
        signUpActivity = myActivityTestRule.getActivity();
    }

    //DISCLAIMER: When running these test cases, make sure the email "test@uottawa.ca" is not present in the database.


    //Tests if "Name is required" text appears when the user enters a wrong first name
    @Test
    public void FirstNameIsRequired() {
        ViewInteraction editText = onView(withId(R.id.firstNameNewUser)).perform(typeText(""),closeSoftKeyboard());
        onView(withId(R.id.lastNameNewUser)).perform(typeText("dhouib"),closeSoftKeyboard());
        onView(withId(R.id.emailNewUser)).perform(typeText("test@uottawa.ca"), closeSoftKeyboard());
        onView(withId(R.id.passwordNewUser)).perform(typeText("123456"),closeSoftKeyboard());
        onView(withId(R.id.confirmNewUserPassword)).perform(typeText("123456"),closeSoftKeyboard());
        onView(withId(R.id.phoneNumberNewUser)).perform(typeText("6137360153"),closeSoftKeyboard());
        onView(withId(R.id.addressNewUser)).perform(typeText("2149 johnston road"),closeSoftKeyboard());
        onView(withId(R.id.bRegister)).perform(click());
        editText.check(matches(hasErrorText("Name is required")));

    }

    //Tests if "Please enter a valid name" text appears when the user enters an last
    @Test
    public void FirstNameIsInvalid() {
        ViewInteraction editText = onView(withId(R.id.firstNameNewUser)).perform(typeText("123test"),closeSoftKeyboard());
        onView(withId(R.id.lastNameNewUser)).perform(typeText("dhouib"),closeSoftKeyboard());
        onView(withId(R.id.emailNewUser)).perform(typeText("test@uottawa.ca"), closeSoftKeyboard());
        onView(withId(R.id.passwordNewUser)).perform(typeText("123456"),closeSoftKeyboard());
        onView(withId(R.id.confirmNewUserPassword)).perform(typeText("123456"),closeSoftKeyboard());
        onView(withId(R.id.phoneNumberNewUser)).perform(typeText("6137360153"),closeSoftKeyboard());
        onView(withId(R.id.addressNewUser)).perform(typeText("2149 johnston road"),closeSoftKeyboard());
        onView(withId(R.id.bRegister)).perform(click());
        editText.check(matches(hasErrorText("Please enter a valid name")));

    }

    @Test
    public void LastNameIsRequired() {
        onView(withId(R.id.firstNameNewUser)).perform(typeText("test"),closeSoftKeyboard());
        ViewInteraction editText = onView(withId(R.id.lastNameNewUser)).perform(typeText(""),closeSoftKeyboard());
        onView(withId(R.id.emailNewUser)).perform(typeText("test@uottawa.ca"), closeSoftKeyboard());
        onView(withId(R.id.passwordNewUser)).perform(typeText("123456"),closeSoftKeyboard());
        onView(withId(R.id.confirmNewUserPassword)).perform(typeText("123456"),closeSoftKeyboard());
        onView(withId(R.id.phoneNumberNewUser)).perform(typeText("6137360153"),closeSoftKeyboard());
        onView(withId(R.id.addressNewUser)).perform(typeText("2149 johnston road"),closeSoftKeyboard());
        onView(withId(R.id.bRegister)).perform(click());
        editText.check(matches(hasErrorText("Last name is required")));

    }

    @Test
    public void LastNameIsInvalid() {
        onView(withId(R.id.firstNameNewUser)).perform(typeText("test"),closeSoftKeyboard());
        ViewInteraction editText = onView(withId(R.id.lastNameNewUser)).perform(typeText("123@"),closeSoftKeyboard());
        onView(withId(R.id.emailNewUser)).perform(typeText("test@uottawa.ca"), closeSoftKeyboard());
        onView(withId(R.id.passwordNewUser)).perform(typeText("123456"),closeSoftKeyboard());
        onView(withId(R.id.confirmNewUserPassword)).perform(typeText("123456"),closeSoftKeyboard());
        onView(withId(R.id.phoneNumberNewUser)).perform(typeText("6137360153"),closeSoftKeyboard());
        onView(withId(R.id.addressNewUser)).perform(typeText("2149 johnston road"),closeSoftKeyboard());
        onView(withId(R.id.bRegister)).perform(click());
        editText.check(matches(hasErrorText("Please enter a valid last name")));
    }

    @Test
    public void EmailIsNotPresent(){
        onView(withId(R.id.firstNameNewUser)).perform(typeText("test"),closeSoftKeyboard());
        onView(withId(R.id.lastNameNewUser)).perform(typeText("login"),closeSoftKeyboard());
        ViewInteraction editText = onView(withId(R.id.emailNewUser)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.passwordNewUser)).perform(typeText("123456"),closeSoftKeyboard());
        onView(withId(R.id.confirmNewUserPassword)).perform(typeText("123456"),closeSoftKeyboard());
        onView(withId(R.id.phoneNumberNewUser)).perform(typeText("6137360153"),closeSoftKeyboard());
        onView(withId(R.id.addressNewUser)).perform(typeText("100 test road"),closeSoftKeyboard());
        onView(withId(R.id.bRegister)).perform(click());
        editText.check(matches(hasErrorText("Email is required")));
    }


    @Test
    public void EmailIsInvalid() {
        onView(withId(R.id.firstNameNewUser)).perform(typeText("test"),closeSoftKeyboard());
        onView(withId(R.id.lastNameNewUser)).perform(typeText("login"),closeSoftKeyboard());
        ViewInteraction editText = onView(withId(R.id.emailNewUser)).perform(typeText("noAtInEmail"), closeSoftKeyboard());
        onView(withId(R.id.passwordNewUser)).perform(typeText("123456"),closeSoftKeyboard());
        onView(withId(R.id.confirmNewUserPassword)).perform(typeText("123456"),closeSoftKeyboard());
        onView(withId(R.id.phoneNumberNewUser)).perform(typeText("6137360153"),closeSoftKeyboard());
        onView(withId(R.id.addressNewUser)).perform(typeText("100 test road"),closeSoftKeyboard());
        onView(withId(R.id.bRegister)).perform(click());
        editText.check(matches(hasErrorText("Please enter a valid email")));
    }

    @Test
    public void PasswordIsRequired(){
        onView(withId(R.id.firstNameNewUser)).perform(typeText("test"),closeSoftKeyboard());
        onView(withId(R.id.lastNameNewUser)).perform(typeText("login"),closeSoftKeyboard());
        onView(withId(R.id.emailNewUser)).perform(typeText("test@uottawa.ca"), closeSoftKeyboard());
        ViewInteraction editText = onView(withId(R.id.passwordNewUser)).perform(typeText(""),closeSoftKeyboard());
        onView(withId(R.id.confirmNewUserPassword)).perform(typeText("123456"),closeSoftKeyboard());
        onView(withId(R.id.phoneNumberNewUser)).perform(typeText("6137360153"),closeSoftKeyboard());
        onView(withId(R.id.addressNewUser)).perform(typeText("100 test road"),closeSoftKeyboard());
        onView(withId(R.id.bRegister)).perform(click());
        editText.check(matches(hasErrorText("Password is required")));
    }

    @Test
    public void PasswordIsTooShortCheck(){
        onView(withId(R.id.firstNameNewUser)).perform(typeText("test"),closeSoftKeyboard());
        onView(withId(R.id.lastNameNewUser)).perform(typeText("login"),closeSoftKeyboard());
        onView(withId(R.id.emailNewUser)).perform(typeText("test@uottawa.ca"), closeSoftKeyboard());
        ViewInteraction editText = onView(withId(R.id.passwordNewUser)).perform(typeText("12345"),closeSoftKeyboard());
        onView(withId(R.id.confirmNewUserPassword)).perform(typeText("12345"),closeSoftKeyboard());
        onView(withId(R.id.phoneNumberNewUser)).perform(typeText("6137360153"),closeSoftKeyboard());
        onView(withId(R.id.addressNewUser)).perform(typeText("100 test road"),closeSoftKeyboard());
        onView(withId(R.id.bRegister)).perform(click());
        editText.check(matches(hasErrorText("Minimum length of password should be 6")));
    }

    @Test
    public void PasswordConfirmIsEmpty(){
        onView(withId(R.id.firstNameNewUser)).perform(typeText("test"),closeSoftKeyboard());
        onView(withId(R.id.lastNameNewUser)).perform(typeText("login"),closeSoftKeyboard());
        onView(withId(R.id.emailNewUser)).perform(typeText("test@uottawa.ca"), closeSoftKeyboard());
        onView(withId(R.id.passwordNewUser)).perform(typeText("123456"),closeSoftKeyboard());
        ViewInteraction editText = onView(withId(R.id.confirmNewUserPassword)).perform(typeText(""),closeSoftKeyboard());
        onView(withId(R.id.phoneNumberNewUser)).perform(typeText("6137360153"),closeSoftKeyboard());
        onView(withId(R.id.addressNewUser)).perform(typeText("100 test road"),closeSoftKeyboard());
        onView(withId(R.id.bRegister)).perform(click());
        editText.check(matches(hasErrorText("You need to confirm your password")));
    }

    @Test
    public void PasswordConfirmIsNotSame(){
        onView(withId(R.id.firstNameNewUser)).perform(typeText("test"),closeSoftKeyboard());
        onView(withId(R.id.lastNameNewUser)).perform(typeText("login"),closeSoftKeyboard());
        onView(withId(R.id.emailNewUser)).perform(typeText("test@uottawa.ca"), closeSoftKeyboard());
        onView(withId(R.id.passwordNewUser)).perform(typeText("123456"),closeSoftKeyboard());
        ViewInteraction editText = onView(withId(R.id.confirmNewUserPassword)).perform(typeText("1234567"),closeSoftKeyboard());
        onView(withId(R.id.phoneNumberNewUser)).perform(typeText("6137360153"),closeSoftKeyboard());
        onView(withId(R.id.addressNewUser)).perform(typeText("100 test road"),closeSoftKeyboard());
        onView(withId(R.id.bRegister)).perform(click());
        editText.check(matches(hasErrorText("Passwords do not match")));
    }


    @Test
    public void PhoneNumIsEmpty(){
        onView(withId(R.id.firstNameNewUser)).perform(typeText("test"),closeSoftKeyboard());
        onView(withId(R.id.lastNameNewUser)).perform(typeText("login"),closeSoftKeyboard());
        onView(withId(R.id.emailNewUser)).perform(typeText("test@uottawa.ca"), closeSoftKeyboard());
        onView(withId(R.id.passwordNewUser)).perform(typeText("123456"),closeSoftKeyboard());
        onView(withId(R.id.confirmNewUserPassword)).perform(typeText("123456"),closeSoftKeyboard());
        ViewInteraction editText = onView(withId(R.id.phoneNumberNewUser)).perform(typeText(""),closeSoftKeyboard());
        onView(withId(R.id.addressNewUser)).perform(typeText("100 test road"),closeSoftKeyboard());
        onView(withId(R.id.bRegister)).perform(click());
        editText.check(matches(hasErrorText("Phone number is required")));
    }

    @Test
    public void AddressIsEmpty(){
        onView(withId(R.id.firstNameNewUser)).perform(typeText("test"),closeSoftKeyboard());
        onView(withId(R.id.lastNameNewUser)).perform(typeText("login"),closeSoftKeyboard());
        onView(withId(R.id.emailNewUser)).perform(typeText("test@uottawa.ca"), closeSoftKeyboard());
        onView(withId(R.id.passwordNewUser)).perform(typeText("123456"),closeSoftKeyboard());
        onView(withId(R.id.confirmNewUserPassword)).perform(typeText("123456"),closeSoftKeyboard());
        onView(withId(R.id.phoneNumberNewUser)).perform(typeText("6137360153"),closeSoftKeyboard());
        ViewInteraction editText = onView(withId(R.id.addressNewUser)).perform(typeText(""),closeSoftKeyboard());
        onView(withId(R.id.bRegister)).perform(click());
        editText.check(matches(hasErrorText("Address is required")));
    }

    @Test
    public void AddressIsInvalid(){
        onView(withId(R.id.firstNameNewUser)).perform(typeText("test"),closeSoftKeyboard());
        onView(withId(R.id.lastNameNewUser)).perform(typeText("login"),closeSoftKeyboard());
        onView(withId(R.id.emailNewUser)).perform(typeText("test@uottawa.ca"), closeSoftKeyboard());
        onView(withId(R.id.passwordNewUser)).perform(typeText("123456"),closeSoftKeyboard());
        onView(withId(R.id.confirmNewUserPassword)).perform(typeText("123456"),closeSoftKeyboard());
        onView(withId(R.id.phoneNumberNewUser)).perform(typeText("6137360153"),closeSoftKeyboard());
        ViewInteraction editText = onView(withId(R.id.addressNewUser)).perform(typeText("20"),closeSoftKeyboard());
        onView(withId(R.id.bRegister)).perform(click());
        editText.check(matches(hasErrorText("Please enter a valid address")));
    }

}
