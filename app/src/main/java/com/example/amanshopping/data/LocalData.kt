package com.example.amanshopping.data

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject


class SharedPreferencesHelper @Inject constructor(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    companion object {
        private const val LOGIN_STATUS_KEY = "login_status"
    }

    fun saveLoginStatus(isLoggedIn: Boolean) {
        sharedPreferences.edit().putBoolean(LOGIN_STATUS_KEY, isLoggedIn).apply()
    }

    fun isLoginStatusSaved(): Boolean {
        return sharedPreferences.getBoolean(LOGIN_STATUS_KEY, false)
    }

    fun logout() {
        sharedPreferences.edit().clear()?.apply()
    }
}
