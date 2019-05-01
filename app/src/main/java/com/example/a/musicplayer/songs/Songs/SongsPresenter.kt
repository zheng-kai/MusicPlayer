package com.example.a.musicplayer.songs.Songs

import android.util.Log
import com.example.a.musicplayer.songs.Songs.Data.SongsBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SongsPresenter(val UI: Service.UI) : Service.Presenter {
    private val model = SongsModel()
    override fun getData(id: String) {
        val call = model.getDetail(id)
        call.enqueue(object : Callback<SongsBean> {
            override fun onFailure(call: Call<SongsBean>, t: Throwable) {
                UI.onError()
                Log.d("onFailure", t.message)
            }

            override fun onResponse(call: Call<SongsBean>, response: Response<SongsBean>) {
                val bean = response.body()
                UI.success(bean?.playlist?.tracks)
                Log.d("onResponse","success")
            }
        })
    }
}