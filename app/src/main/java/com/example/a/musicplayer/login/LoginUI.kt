package com.example.a.musicplayer.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.a.musicplayer.R

class LoginUI : AppCompatActivity() {
    private val LOGIN_BY_EMAIL = 0
    private val LOGIN_BY_PHONE = 1
    lateinit var buttonPhone: Button
    lateinit var buttonEmail: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initID()
        SetOnClick()
    }

    fun initID() {
        buttonEmail = findViewById(R.id.button_email)
        buttonPhone = findViewById(R.id.button_phone)
    }
    fun SetOnClick(){
        buttonEmail.setOnClickListener {
            val intent = Intent(this,LoginUI_2::class.java)
            intent.putExtra("type",LOGIN_BY_EMAIL)
            startActivity(intent)
        }
        buttonPhone.setOnClickListener {
            val intent = Intent(this,LoginUI_2::class.java)
            intent.putExtra("type",LOGIN_BY_PHONE)
            startActivity(intent)
        }
    }
}
