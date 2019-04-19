package com.example.a.musicplayer.songs.playlist

import com.example.a.musicplayer.mService
import com.example.a.musicplayer.songs.playlist.data.PlayListBean
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query

class Model{
    private val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://192.168.146.1:3000/")
        .build()
    private var service = retrofit.create(mService::class.java)
    fun getPlayList(@Query("uid") uid:Int) : Call<PlayListBean>{
        return service.getPlayList(uid)
    }
}