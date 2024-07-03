package com.example.myapplication

import android.app.Application
import android.content.SharedPreferences
import com.example.myapplication.network.ConfigurationApi

class MyApplication : Application() {
    private var blockedPackageNames: ArrayList<String> = arrayListOf()
    private lateinit var prefs: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        instance = this
        prefs = getSharedPreferences(APP_SETTINGS, MODE_PRIVATE)

        fetchIgnoredPackageNames()
    }

    private fun fetchIgnoredPackageNames() {
            val configuration = ConfigurationApi()
            configuration.fetchConfig {
                it?.let {
                    blockedPackageNames.addAll(it.record)
                }
            }
    }

    fun shouldBlockPackageNotifications(packageId: String): Boolean {
        return if (blockedPackageNames.isEmpty()) false else blockedPackageNames.contains(packageId)
    }

    fun shouldShowNotificationsPopUp(): Boolean {
        return !prefs.getBoolean(KEY_POP_UP_SHOW, false)
    }

    fun setNotificationsPopUpShown() {
        prefs.edit().putBoolean(KEY_POP_UP_SHOW, true).apply()
    }

    companion object {
        private const val APP_SETTINGS = "AppSettings"
        private const val KEY_POP_UP_SHOW = "POP_UP_SHOWN"

        lateinit var instance: MyApplication
    }
}