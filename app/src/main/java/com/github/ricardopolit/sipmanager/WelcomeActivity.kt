package com.github.ricardopolit.sipmanager

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
    }

//    override fun getTheme(): Resources.Theme {
//        val theme = super.getTheme()
//        theme.applyStyle(R.style.Theme_SIPManager,true)
//        return theme
//    }
}