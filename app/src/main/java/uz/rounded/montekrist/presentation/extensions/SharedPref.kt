package uz.rounded.montekrist.presentation.extensions

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import javax.inject.Inject

class SharedPref @Inject constructor(app: Context) {

    private val prefsName: String = "MontekristPref"
    private var prefs: SharedPreferences

    val isShown = "guide"

    init {
        prefs = app.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        Log.d("TTT", prefsName)
    }

    fun save(key: String, value: Boolean) {
        prefs.edit().putBoolean(key, value).apply()
    }

    fun get(key: String, defValue: Boolean) = prefs.getBoolean(key, defValue)

    fun clear() {
        prefs.edit().clear().apply()
    }
}
