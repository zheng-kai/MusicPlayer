package com.example.a.musicplayer.songs.Songs

import com.example.a.musicplayer.songs.Songs.Data.Track
import com.example.a.musicplayer.songs.playlist.data.Playlist

interface Service {
    interface Presenter{
        fun getData(id:String)
    }
    interface UI{
        fun onError()
        fun success(list:List<Track>?)
    }
}