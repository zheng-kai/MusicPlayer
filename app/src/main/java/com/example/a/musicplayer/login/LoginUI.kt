package com.example.a.musicplayer.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.Toast
import com.example.a.musicplayer.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginUI : AppCompatActivity() {
    private val LOGIN_BY_EMAIL = 0
    private val LOGIN_BY_PHONE = 1
    private var notClicked = true
    lateinit var buttonPhone: Button
    lateinit var buttonEmail: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
        SetOnClick()
    }

    fun init() {
        notClicked = true
        buttonEmail = findViewById(R.id.button_email)
        buttonPhone = findViewById(R.id.button_phone)
    }

    fun SetOnClick() {
        val intent = Intent(this, LoginUI_2::class.java)
        buttonEmail.setOnClickListener {
            if (notClicked) {
                notClicked = false
                intent.putExtra("type", LOGIN_BY_EMAIL)
                startActivity(intent)
                GlobalScope.launch {
                    delay(1L)
                    notClicked = true
                }
            }
        }
        buttonPhone.setOnClickListener {
            if (notClicked) {
                notClicked = false
                intent.putExtra("type", LOGIN_BY_PHONE)
                startActivity(intent)
                GlobalScope.launch {
                    delay(1L)
                    notClicked = true
                }
            }
        }
    }
}
