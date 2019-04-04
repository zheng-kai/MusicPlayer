package com.example.a.musicplayer

import com.example.a.musicplayer.Data.LoginBean
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.POST
import javax.security.auth.callback.Callback

interface mService {
    @POST("/login/cellphone")
    fun loginByPhone(@Field("phone") phone : String,@Field("password")password : String) : Call<LoginBean>
    @POST("/login")
    fun loginByEmail(@Field("email") phone : String,@Field("password")password : String) : Call<LoginBean>

}