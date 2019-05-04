package com.example.a.musicplayer.songs.player

import android.animation.ObjectAnimator
import android.graphics.drawable.Drawable
import com.example.a.musicplayer.songs.player.data.MyStoreBean
import com.example.a.musicplayer.songs.player.data.SongBean
import com.example.a.musicplayer.songs.playlist.data.Playlist

interface PlayerService {
    interface Presenter{
        fun getData(id:String)
    }
    interface UI{
        fun onError()
        fun onNull()
        fun successSong(bean:SongBean)
        fun successLyric(lyric:String?)
        fun loadLyrics(lyric:ArrayList<String?>,time:ArrayList<String?>)
        fun load(mBean:MyStoreBean)
        fun load()
    }
}