package com.example.a.musicplayer.songs.player

import android.app.Service
import android.content.Intent
import android.content.ServiceConnection
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder

class MyService : Service(){
    private var myBinder = MyBinder()
    private var mediaPlayer:MediaPlayer? = null
    inner class MyBinder : Binder(){

    }

    override fun onBind(intent: Intent?): IBinder? {
        return myBinder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (mediaPlayer) {
            null -> mediaPlayer = MediaPlayer()
        }

        mediaPlayer?.let {
            if(it.isPlaying){
                it.reset()
            }
            it.setDataSource(intent?.getStringExtra("url"))
            it.prepare()
            it.start()
        }
        return super.onStartCommand(intent, flags, startId)
    }

}