package com.example.a.musicplayer.songs.player

import android.animation.ObjectAnimator
import android.util.Log
import com.example.a.musicplayer.R

class PlayList private constructor() {
    private lateinit var UI: PlayerService.UI
    private var position = -1
    private var anis = ArrayList<ObjectAnimator?>()
    private var picList = ArrayList<String?>()

    companion object {
        val playList: PlayList by lazy {
            PlayList()
        }
    }

    fun setUI(UI: PlayerService.UI) {
        this.UI = UI
    }

    fun storeData(picUrl: String?, animator: ObjectAnimator?) {
        if (!picList.contains(picUrl)) {
            position++
            picList.add(picUrl)
            anis.add(animator)
        }
        Log.d("picList", picList.toString())
    }
    fun play(){
        if(position == -1){
            UI.loadImage()
        }else{
            UI.loadImage(picList[position])
        }
    }
    fun playPrior() {
        position--
        when (position) {
            -1 -> position = picList.size - 1
        }
        UI.loadImage(picList[position])
    }

    fun playNext() {
        position++
        when (position) {
            picList.size -> position = 0
        }
        UI.loadImage(picList[position])
    }
    fun isEmpty():Boolean{
        return picList.isEmpty()
    }
}