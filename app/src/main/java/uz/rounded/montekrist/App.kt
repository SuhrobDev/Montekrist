package uz.rounded.montekrist

import android.app.Application
import android.content.res.Resources
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    companion object {
        lateinit var resources: Resources
    }

    override fun onCreate() {
        super.onCreate()
        Companion.resources = resources
    }
}