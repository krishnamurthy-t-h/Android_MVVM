package com.krishna.mvvm.data.preferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager


private const val KEY_SAVED_AT="key_saved_at"
class PreferenceProvider(
    context: Context
) {

    private val appContext = context.applicationContext

    private val preference : SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun saveLastSavedAt(savedat: String){
        preference.edit().putString(KEY_SAVED_AT,savedat).apply()
    }

    fun getLastSavedAt():String?{
        return preference.getString(KEY_SAVED_AT,null)
    }

}