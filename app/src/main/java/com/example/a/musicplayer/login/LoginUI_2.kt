package com.example.a.musicplayer.login

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.a.musicplayer.R
import com.example.a.musicplayer.Songs

class LoginUI_2 : AppCompatActivity() ,LoginService.UI{
    private val LOGIN_BY_EMAIL = 0
    private val LOGIN_BY_PHONE = 1
    lateinit var accEdit : EditText
    lateinit var passwordEdit : EditText
    lateinit var button : Button
    var type : Int = 0
    private val presenter = LoginPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_2)
        initID()
        type = recType()
        if(type == LOGIN_BY_PHONE){
            accEdit.hint = "请输入电话号码"
        }
        if(type == LOGIN_BY_EMAIL){
            accEdit.hint = "请输入邮箱"
        }
        button.setOnClickListener {
            val account = accEdit.text.toString()
            val password = passwordEdit.text.toString()
            presenter.getData(account, password, type)
        }
    }
    fun recType() : Int{
        return intent.getIntExtra("type",-1)
    }
    fun initID(){
        button = findViewById(R.id.login)
        accEdit = findViewById(R.id.edit_account)
        passwordEdit = findViewById(R.id.edit_password)
    }
    override fun onError() {
        Toast.makeText(this,"请检查网络", Toast.LENGTH_LONG).show()
    }

    override fun addData() {
        val intent2 = Intent(this, Songs::class.java)
        startActivity(intent2)
    }

    override fun loginFailure(message: String) {
        Toast.makeText(this,"message", Toast.LENGTH_LONG).show()
    }
}