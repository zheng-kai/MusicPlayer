package com.example.a.musicplayer.songs.player

import android.util.Log
import com.example.a.musicplayer.songs.player.data.LyricBean
import com.example.a.musicplayer.songs.player.data.SongBean
import com.example.a.musicplayer.songs.playlist.data.PlayListBean
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Presenter(val UI: PlayerService.UI) : PlayerService.Presenter {
    private val model = Model()
    override fun getData(id: String) {
        val call = model.getSong(id)
        call.enqueue(object : Callback<SongBean> {
            override fun onFailure(call: Call<SongBean>, t: Throwable) {
                UI.onError()
                Log.d("onFailure", t.message)
            }

            override fun onResponse(call: Call<SongBean>, response: Response<SongBean>) {
                val bean = response.body()
                if (bean?.data?.get(0)?.url != null) {
                    UI.successSong(bean)
                } else {
                    UI.onNull()
                }
            }

        })
        val call2 = model.getLyric(id)
        call2.enqueue(object:Callback<LyricBean>{
            override fun onFailure(call: Call<LyricBean>, t: Throwable) {
                Log.d("onFailure",t.message)
            }

            override fun onResponse(call: Call<LyricBean>, response: Response<LyricBean>) {
                UI.successLyric(response.body()?.lrc?.lyric)
            }

        })


    }
}