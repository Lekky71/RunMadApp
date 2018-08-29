package com.hashcode.runmad

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics

/**
 * Created by HashCode on 11:25 AM 29/08/2018.
 */
class ApplicationInstance: Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseAnalytics.getInstance(applicationContext)

    }
}