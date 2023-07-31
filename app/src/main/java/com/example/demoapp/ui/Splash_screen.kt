package com.example.demoapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.demoapp.R

class Splash_screen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val handler = Handler()
        handler.postDelayed({

            startActivity(Intent(this, MainActivity::class.java))
        }, 1000)

        //onBackPressed()
    }

    override fun onBackPressed() {
        finish()
    }
}

