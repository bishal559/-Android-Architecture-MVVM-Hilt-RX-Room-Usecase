package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.data.persistence

import android.content.Context
import android.content.SharedPreferences
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.BuildConfig
import com.google.gson.Gson
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class SharedPrefs  @Inject constructor( context: Context) {
    private val mSharedPreferences: SharedPreferences

    init {
        mSharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)!!
    }

    companion object : SingletonHolder<SharedPrefs, Context>(::SharedPrefs) {
        private const val PREFS_NAME = "share_prefs" + BuildConfig.APPLICATION_ID
    }

    operator fun <T> get(key: String, anonymousClass: Class<T>): T {
        return when (anonymousClass) {
            String::class.java -> mSharedPreferences.getString(key, "") as T
            Boolean::class.java -> mSharedPreferences.getBoolean(key, false) as T
            Float::class.java -> mSharedPreferences.getFloat(key, 0f) as T
            Int::class.java -> mSharedPreferences.getInt(key, 0) as T
            Long::class.java -> mSharedPreferences.getLong(key, 0) as T
            else -> Gson().fromJson(mSharedPreferences.getString(key, ""), anonymousClass)
        }
    }

    fun <T> put(key: String, data: T) {
        val editor = mSharedPreferences.edit()
        when (data) {
            is String -> editor.putString(key, data as String)
            is Boolean -> editor.putBoolean(key, data as Boolean)
            is Float -> editor.putFloat(key, data as Float)
            is Int -> editor.putInt(key, data as Int)
            is Long -> editor.putLong(key, data as Long)
            else -> editor.putString(key, Gson().toJson(data))
        }
        editor.apply()
    }

    fun clear() {
        mSharedPreferences.edit().clear().apply()
    }

    fun clearDataByKey(key: String) {
        mSharedPreferences.edit().remove(key).apply()
    }
}