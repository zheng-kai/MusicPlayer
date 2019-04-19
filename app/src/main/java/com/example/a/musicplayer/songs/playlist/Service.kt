package com.example.a.musicplayer.songs.playlist

import com.example.a.musicplayer.songs.playlist.data.Playlist

interface Service {
    interface PLPresenter{
        fun getData(uid:Int)
    }
    interface PLUI{
        fun onError()
        fun success(list:List<Playlist?>?)
    }
}