package com.example.amanshopping

import androidx.navigation.Navigation
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.amanshopping.MainActivity
import com.example.amanshopping.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun testMainActivityDisplayed() {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        Espresso.onView(ViewMatchers.withId(R.id.main_activity))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        activityScenario.close()
    }

    @Test
    fun testBackPressedInShopingFragment() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        activityScenario.onActivity { activity ->
            val navController = Navigation.findNavController(activity, R.id.navigation)
            activity.runOnUiThread {
                navController.navigate(R.id.shopingFragment)
            }
        }

        Espresso.pressBack()

        activityScenario.onActivity { activity ->
            assert(activity.isFinishing)
        }
        activityScenario.close()
    }

}
