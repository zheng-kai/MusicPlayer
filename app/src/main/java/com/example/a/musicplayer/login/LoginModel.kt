package com.example.a.musicplayer.login

import com.example.a.musicplayer.login.Data.LoginBean
import com.example.a.musicplayer.mService
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginModel {
    private val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.146.1:3000/")
        .build()
    private var service = retrofit.create(mService::class.java)
    fun loginByPhone(phone:Long,password:String):Call<LoginBean>{
        return  service.loginByPhone(phone,password)
    }
    fun loginByEmail(email:String,password: String):Call<LoginBean>{
        return service.loginByEmail(email,password)
    }
}