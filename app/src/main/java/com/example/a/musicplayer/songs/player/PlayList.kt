package com.example.a.musicplayer.songs.player

import android.util.Log
import com.example.a.musicplayer.songs.player.data.MyStoreBean

class PlayList private constructor() {
    private lateinit var UI: PlayerService.UI
    private var position = -1
    private var picList = ArrayList<String?>()
    private var titleList = ArrayList<String>()
    private var subtitleList = ArrayList<String>()
    private var lyric = ArrayList<ArrayList<String?>>()
    private var time = ArrayList<ArrayList<String?>>()
    private var storeBean = MyStoreBean(null,"","")
    companion object {
        val playList: PlayList by lazy {
            PlayList()
        }
    }

    fun setUI(UI: PlayerService.UI) {
        this.UI = UI
    }
    fun storeData(lyric:ArrayList<String?>,time:ArrayList<String?>){
        this.lyric.add(lyric)
        this.time.add(time)
    }
    fun storeData(picUrl: String?,title:String,subtitle:String) {
        if (!picList.contains(picUrl)) {
            position++
            picList.add(picUrl)
            titleList.add(title)
            subtitleList.add(subtitle)
        }
        Log.d("picUrl", picList.toString())
    }
    fun play(){
        if(position == -1){
            UI.load()
        }else{
            storeBean.picUrl = picList[position]
            storeBean.subtitle = subtitleList[position]
            storeBean.title = titleList[position]
            UI.load(storeBean)
        }
    }
    fun playPrior() {
        position--
        when (position) {
            -1 -> position = picList.size - 1
        }
        storeBean.picUrl = picList[position]
        storeBean.subtitle = subtitleList[position]
        storeBean.title = titleList[position]
        UI.loadLyrics(lyric[position],time[position])
        UI.load(storeBean)
    }

    fun playNext() {
        position++
        when (position) {
            picList.size -> position = 0
        }
        storeBean.picUrl = picList[position]
        storeBean.subtitle = subtitleList[position]
        storeBean.title = titleList[position]
        UI.loadLyrics(lyric[position],time[position])
        UI.load(storeBean)
    }
    fun isEmpty():Boolean{
        return picList.isEmpty()
    }
}