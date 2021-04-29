package com.github.ricardopolit.sipmanager.util

import android.content.Context
import com.google.gson.Gson

object Preference {

    private const val SHARED_PREFERENCE_FILENAME =
            "com.github.ricardopolit.sipmanager.PREFERENCE_FILE_KEY"

    fun storePreferenceFlag(
            context: Context,
            prefKey: String,
            value: Boolean
    ){
        context.getSharedPreferences(SHARED_PREFERENCE_FILENAME, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(prefKey,value).apply()
    }

    fun getPreferenceFlag(
            context: Context,
            prefKey: String
    ): Boolean {
        return context.getSharedPreferences(SHARED_PREFERENCE_FILENAME, Context.MODE_PRIVATE)
                .getBoolean(prefKey,false)
    }

    fun storePreferenceString(
            context: Context,
            prefKey: String,
            value: String
    ){
        context.getSharedPreferences(SHARED_PREFERENCE_FILENAME, Context.MODE_PRIVATE)
                .edit()
                .putString(prefKey,value).apply()
    }

    fun getPreferenceString(
            context: Context,
            prefKey: String
    ): String? {
        return context.getSharedPreferences(SHARED_PREFERENCE_FILENAME, Context.MODE_PRIVATE)
                .getString(prefKey,null)
    }

    fun storeEncryptedValue(
            context: Context,
            prefKey: String,
            encryptedValue: EncryptedValue
    ){
        val json = Gson().toJson(encryptedValue)
        context.getSharedPreferences(SHARED_PREFERENCE_FILENAME, Context.MODE_PRIVATE)
                .edit()
                .putString(prefKey, json).apply()
    }

    fun getEncryptedValue(
            context: Context,
            prefKey: String
    ): EncryptedValue?{
        val json = context.getSharedPreferences(SHARED_PREFERENCE_FILENAME, Context.MODE_PRIVATE)
                .getString(prefKey,null)
        return Gson().fromJson(json, EncryptedValue::class.java)
    }

}