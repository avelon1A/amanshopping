package com.example.amanshopping.fragment

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.amanshopping.R
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginFragmentTest {
    private lateinit var  scenario: FragmentScenario<LoginFragment>

    @Before
    fun setUp() {
       scenario = launchFragmentInContainer(themeResId = R.style.Theme_Amanshopping)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun testLoginSuccess() {
        Espresso.onView(ViewMatchers.withId(R.id.usernameEditText))
            .perform(ViewActions.typeText("valid@example.com"))

        Espresso.onView(ViewMatchers.withId(R.id.passwordEditText))
            .perform(ViewActions.typeText("password123"))

        Espresso.onView(ViewMatchers.withId(R.id.loginButton))
            .perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.shopingFragment))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testLoginFailure() {
        Espresso.onView(ViewMatchers.withId(R.id.usernameEditText))
            .perform(ViewActions.typeText("invalid@example.com"))

        Espresso.onView(ViewMatchers.withId(R.id.passwordEditText))
            .perform(ViewActions.typeText("invalidpassword"))

        Espresso.onView(ViewMatchers.withId(R.id.loginButton))
            .perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withText(R.string.loggin_failed))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @After
    fun tearDown() {

    }
}
