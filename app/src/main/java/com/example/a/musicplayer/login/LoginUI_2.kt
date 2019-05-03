package com.example.a.musicplayer.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.a.musicplayer.R
import com.example.a.musicplayer.login.Data.LoginBean
import com.example.a.musicplayer.songs.playlist.UI

class LoginUI_2 : AppCompatActivity(), LoginService.UI {
    private val LOGIN_BY_EMAIL = 0
    private val LOGIN_BY_PHONE = 1
    private var notClicked = true
    private lateinit var toolbar: Toolbar
    private lateinit var accEdit: EditText
    private lateinit var passwordEdit: EditText
    private lateinit var button: Button
    private lateinit var backBtn: Button
    private var type: Int = 0
    private val presenter = LoginPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_2)
        initID()
        type = recType()
        if (type == LOGIN_BY_PHONE) {
            accEdit.hint = "请输入电话号码"
        }
        if (type == LOGIN_BY_EMAIL) {
            accEdit.hint = "请输入邮箱"
        }

        button.setOnClickListener {
            val account = accEdit.text.toString().toLong()
            val password = passwordEdit.text.toString()
            if (notClicked) {
                notClicked = false
                presenter.getData(account, password, type)
            }
        }
        backBtn.setOnClickListener {
            onBackPressed()
        }
    }

    fun recType(): Int {
        return intent.getIntExtra("type", -1)
    }

    fun initID() {
        toolbar = findViewById(R.id.toolbar_login2)
        setSupportActionBar(toolbar)
        backBtn = toolbar.findViewById(R.id.back_login2)
        button = findViewById(R.id.login)
        accEdit = findViewById(R.id.edit_account)
        passwordEdit = findViewById(R.id.edit_password)
    }

    override fun onError() {
        notClicked = true
        Toast.makeText(this, "请检查网络", Toast.LENGTH_LONG).show()
    }

    override fun startA(bean:LoginBean) {
        val intent2 = Intent(this, UI::class.java)
        intent2.putExtra("userID", bean.account.id)
        intent2.putExtra("nickname", bean.profile.nickname)
        intent2.putExtra("headPhoto",bean.profile.avatarUrl)
        startActivity(intent2)
        finish()
    }

    override fun loginFailure(message: String?) {
        notClicked = true
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}