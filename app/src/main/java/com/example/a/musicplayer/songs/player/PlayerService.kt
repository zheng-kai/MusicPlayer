package com.example.a.musicplayer.songs.player

import com.example.a.musicplayer.songs.player.data.SongBean
import com.example.a.musicplayer.songs.playlist.data.Playlist

interface PlayerService {
    interface Presenter{
        fun getData(id:String)
    }
    interface UI{
        fun onError()
        fun success(bean:SongBean)
    }
}