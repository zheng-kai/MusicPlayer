package com.example.a.musicplayer.songs.player

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {
    private var list: ArrayList<String?> = ArrayList()
    private var position = -1
    private var id: String? = ""
    private var url: String? = ""
    private var myBinder = MyBinder()
    private var mediaPlayer: MediaPlayer? = null

    inner class MyBinder : Binder() {
        fun getService(): MyService {
            return this@MyService
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return myBinder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        id = intent?.getStringExtra("url")
        Log.d("mmlist", "start! $id")

        play(id)
        mediaPlayer?.setOnErrorListener { mp, what, extra ->
            play(id)
            false
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d("mmlisteee",list.size.toString())
        super.onDestroy()
    }
    private fun playByPosition(position: Int): Boolean {
        when (mediaPlayer) {
            null -> mediaPlayer = MediaPlayer()
        }
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.reset()
            }
            try {
                it.setDataSource(list[position])
                it.prepare()
                it.start()
                return true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return false
    }

    fun play(url: String?): Boolean {
        when (mediaPlayer) {
            null -> mediaPlayer = MediaPlayer()
        }
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.reset()
            }
            try {
                it.setDataSource(url)
                it.prepare()
                it.start()
                list.add(url)
                Log.d("mmlist",list.toString() + "!size! " + list.size.toString() + "!id! $id")
                position++
                return true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return false
    }

    fun playNext() {
        position++
        Log.d("mmlistsize",list.toString() + "!size! " + list.size.toString())
        if(position >= list.size){
            position = 0
        }
        url = list[position]
        if (playByPosition(position)) mediaPlayer?.seekTo(0)
    }

    fun playPrior() {
        position--
        when (position) {
            -1 -> position = list.size - 1
        }
        url = list[position]
        if (playByPosition(position)) mediaPlayer?.seekTo(0)
    }

    fun pauseOrPlay() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
            } else {
                it.start()
            }
        }
    }
    fun isPlaying():Boolean{
        return mediaPlayer?.isPlaying ?: false
    }
    fun getCurrentPosition():Int{
        Log.d("position",mediaPlayer?.currentPosition.toString())
        return mediaPlayer?.currentPosition ?: 0
    }
    fun getDuration():Int{
        Log.d("duration",mediaPlayer?.duration.toString())
        return mediaPlayer?.duration ?: 3
    }
    fun seekTo(position:Int){
        mediaPlayer?.seekTo(position)
    }
}