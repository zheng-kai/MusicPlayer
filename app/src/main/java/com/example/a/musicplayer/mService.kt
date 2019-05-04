package com.example.a.musicplayer

import com.example.a.musicplayer.login.Data.LoginBean
import com.example.a.musicplayer.songs.Songs.Data.SongsBean
import com.example.a.musicplayer.songs.player.data.LyricBean
import com.example.a.musicplayer.songs.player.data.SongBean
import com.example.a.musicplayer.songs.playlist.data.PlayListBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface mService {
    @GET("/login/cellphone")
    fun loginByPhone(@Query("phone") acc: Long, @Query("password") password: String): Call<LoginBean>

    @GET("/login")
    fun loginByEmail(@Query("email") acc: String, @Query("password") password: String): Call<LoginBean>

    @GET("/user/playlist")
    fun getPlayList(@Query("uid") uid: Int): Call<PlayListBean>

    @GET("/playlist/detail")
    fun getDetail(@Query("id") id: String): Call<SongsBean>

    @GET("/song/url")
    fun getSong(@Query("id") id: String): Call<SongBean>

    @GET("/lyric")
    fun getLyric(@Query("id") id: String): Call<LyricBean>
}