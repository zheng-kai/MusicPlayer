package com.example.a.musicplayer.songs.playlist

import android.util.Log
import com.example.a.musicplayer.songs.playlist.data.PlayListBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Presenter(val UI : Service.PLUI) : Service.PLPresenter{
    private val model = Model()
    override fun getData(uid:Int) {
        val call = model.getPlayList(uid)
        call.enqueue(object : Callback<PlayListBean> {
            override fun onFailure(call: Call<PlayListBean>, t: Throwable) {
                UI.onError()
                Log.d("onFailure",t.message)
            }

            override fun onResponse(call: Call<PlayListBean>, response: Response<PlayListBean>) {
                val bean = response.body()

                Log.d("listbean",bean?.playlist.toString())
                val list = bean?.playlist
                bean?.let {
                    UI.success(it.playlist)
                }
//                list?.let {
//                    for(i in it){
                        Log.d("MYList",list?.toString())
//                    }
//                }
            }

        })
    }
}