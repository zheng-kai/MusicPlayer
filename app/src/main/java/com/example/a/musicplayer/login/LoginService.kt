package com.example.a.musicplayer.login

import com.example.a.musicplayer.login.Data.LoginBean


interface LoginService {
    interface Presenter{
        fun getData(acc:Long,password:String,loginType: Int)
    }
    interface UI{
        fun onError()
        fun startA(bean: LoginBean)
        fun loginFailure(message: String?)
    }
}