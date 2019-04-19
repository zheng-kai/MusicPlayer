package com.example.a.musicplayer.login


interface LoginService {
    interface Presenter{
        fun getData(acc:Long,password:String,loginType: Int)
    }
    interface UI{
        fun onError()
        fun startA(userID:Int,nickname: String)
        fun loginFailure(message: String?)
    }
}