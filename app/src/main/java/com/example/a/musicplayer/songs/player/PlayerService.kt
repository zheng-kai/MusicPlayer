package com.example.a.musicplayer.songs.player

import android.animation.ObjectAnimator
import android.graphics.drawable.Drawable
import com.example.a.musicplayer.songs.player.data.SongBean
import com.example.a.musicplayer.songs.playlist.data.Playlist

interface PlayerService {
    interface Presenter{
        fun getData(id:String)
    }
    interface UI{
        fun onError()
        fun onNull()
        fun success(bean:SongBean)
        fun loadImage(picUrl:String?)
        fun loadImage()
    }
}