package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> scenario =
            new ActivityScenarioRule<MainActivity> (MainActivity.class);
    // NOTE : No issues on Pixel 2 emulator - other emulator had issues due to version
    @Test
    public void testAddCity(){
        //click on Add City button
        onView(withId(R.id.button_add)).perform(click());
        //Type "Edmonton" in the edit text
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));
        // Click on the confirm button
        onView(withId(R.id.button_confirm)).perform(click());
        // Check if the text "Edmonton" is matched with any of the text displayed on the screen
        onView(withText("Edmonton")).check(matches(isDisplayed()));

    }

    @Test
    public void testClearCity(){
// Add first city to the list
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonto n"));
        onView(withId(R.id.button_confirm)).perform(click());
//Add another city to the list
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Vancouv er"));
        onView(withId(R.id.button_confirm)).perform(click());
//Clear the list
        onView(withId(R.id.button_clear)).perform(click());
        onView(withText("Edmonton")).check(doesNotExist());
        onView(withText("Vancouver")).check(doesNotExist());
    }

    @Test
    public void testListView(){
// Add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));
                onView(withId(R.id.button_confirm)).perform(click());

  onData(is(instanceOf(String.class))).inAdapterView(withId(R.id.city_list)).atPosition(0).check(matches((withText("Edmonton"))));

    }


    @Test
    public void testSwitch(){
        //click on Add City button
        onView(withId(R.id.button_add)).perform(click());
        //Type "Edmonton" in the edit text
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));
        // Click on the confirm button
        onView(withId(R.id.button_confirm)).perform(click());

        // used gemini for next 2  lines on Feb 26, prompt was 'how can I perform a click on a listview Item at index 0 , in expresson ?'
        // Click on the first item in the list
        onData(is(instanceOf(String.class))).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());
        // Check that ShowActivity is opened by checking if the back button is displayed
        // another way to do this is to compare the id of the button for more explicitness
        onView(withText("Back")).check(matches(isDisplayed()));

    }
    @Test
    public void testCityName(){
        //click on Add City button
        onView(withId(R.id.button_add)).perform(click());
        //Type "Edmonton" in the edit text
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));
        // Click on the confirm button
        onView(withId(R.id.button_confirm)).perform(click());

        // used gemini for next 2  lines on Feb 26, prompt was 'how can I perform a click on a listview Item at index 0 , in expresson ?'
        // Click on the first item in the list
        onData(is(instanceOf(String.class))).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());
        // Check that ShowActivity is opened and displays the city name
        onView(withId(R.id.clicked_city)).check(matches(withText("Edmonton")));
    }

    @Test
    public void testBack(){
        //click on Add City button
        onView(withId(R.id.button_add)).perform(click());
        //Type "Edmonton" in the edit text
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));
        // Click on the confirm button
        onView(withId(R.id.button_confirm)).perform(click());

        // used gemini for next 2  lines on Feb 26, prompt was 'how can I perform a click on a listview Item at index 0 , in expresson ?'
        // Click on the first item in the list
        onData(is(instanceOf(String.class))).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());
        // click back button
        onView(withId(R.id.back_button)).perform(click());
        // click if our add button on main is there
        onView(withId(R.id.button_add)).check(matches(isDisplayed()));
    }

} // end of MainActivityTest class
