package com.github.ricardopolit.sipmanager

import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity: AppCompatActivity() {
    private val INTENT_USER_PASSWORD = "com.github.ricardopolit.sipmanager.P455W0RD_U53R"
    private lateinit var userPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userPassword = intent.getStringExtra(INTENT_USER_PASSWORD).toString()
        setContentView(R.layout.activity_main)
    }

    fun getUserPassword(): String{
        return userPassword
    }

//        override fun getTheme(): Resources.Theme {
//        val theme = super.getTheme()
//        theme.applyStyle(R.style.Theme_SIPManager,true)
//        return theme
//    }

}
