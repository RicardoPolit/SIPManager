package com.github.ricardopolit.sipmanager

import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

//        override fun getTheme(): Resources.Theme {
//        val theme = super.getTheme()
//        theme.applyStyle(R.style.Theme_SIPManager,true)
//        return theme
//    }

}
