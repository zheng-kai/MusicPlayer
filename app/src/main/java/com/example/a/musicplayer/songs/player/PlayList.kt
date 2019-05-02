package com.example.a.musicplayer.songs.player

import android.util.Log

class PlayList private constructor() {
    private lateinit var UI : PlayerService.UI
    private var position = -1
    private var picList = ArrayList<String?>()
    companion object {
        val playList :PlayList by lazy{
            PlayList()
        }
    }
    fun setUI(UI:PlayerService.UI){
        this.UI = UI
    }
    fun storeData(picUrl : String?) {
        if(!picList.contains(picUrl)){
            position++
            picList.add(picUrl)
        }
        Log.d("picList",picList.toString())
    }
    fun playPrior(){
        position--
        when (position) {
            -1 -> position = picList.size - 1
        }
        UI.loadImage(picList[position])
    }
    fun playNext(){
        position++
        when (position) {
            picList.size -> position = 0
        }
        UI.loadImage(picList[position])
    }
}