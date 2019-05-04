package com.example.a.musicplayer.songs.player

import android.animation.ObjectAnimator
import android.util.Log

class PlayList private constructor() {
    private lateinit var UI: PlayerService.UI
    private var position = -1
    private var picList = ArrayList<String?>()
    private var titleList = ArrayList<String>()
    private var subtitleList = ArrayList<String>()
    companion object {
        val playList: PlayList by lazy {
            PlayList()
        }
    }

    fun setUI(UI: PlayerService.UI) {
        this.UI = UI
    }

    fun storeData(picUrl: String?,title:String,subtitle:String) {
        if (!picList.contains(picUrl)) {
            position++
            picList.add(picUrl)
            titleList.add(title)
            subtitleList.add(subtitle)
        }
        Log.d("picList", picList.toString())
    }
    fun play(){
        if(position == -1){
            UI.load()
        }else{
            UI.load(picList[position],titleList[position],subtitleList[position])
        }
    }
    fun playPrior() {
        position--
        when (position) {
            -1 -> position = picList.size - 1
        }
        UI.load(picList[position],titleList[position],subtitleList[position])
    }

    fun playNext() {
        position++
        when (position) {
            picList.size -> position = 0
        }
        UI.load(picList[position],titleList[position],subtitleList[position])
    }
    fun isEmpty():Boolean{
        return picList.isEmpty()
    }
}