package com.example.a.musicplayer.login

interface LoginService {
    interface Presenter{
        fun getData(acc:String,password:String,loginType: Int)
    }
    interface UI{
        fun onError()
        fun addData()
        fun loginFailure(message : String)
    }
}