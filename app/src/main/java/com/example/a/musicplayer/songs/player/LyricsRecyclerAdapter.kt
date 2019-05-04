package com.example.a.musicplayer.songs.player

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.a.musicplayer.R

class LyricsRecyclerAdapter(val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var listLyrics: ArrayList<String?> = ArrayList()
    private var listTime: ArrayList<String?> = ArrayList()
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return Lyrics(LayoutInflater.from(context).inflate(R.layout.recycler_item_player_lyrics,p0,false))
    }

    override fun getItemCount(): Int {
        return listLyrics.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        if(p0 is Lyrics){
            p0.lyricsTextView.text = listLyrics[p1]
            p0.timeTextView.text = listTime[p1]
            Log.d("Lyrics!recy!",listLyrics[p1] + "||" +listTime[p1])
        }
    }
    fun addData(lyric:String?){
        var lyrics = ""
        var time = ""
        var flag = true
        val TIME = 0
        val LYRICS = 1
        var lastAddType = LYRICS
        lyric?.let {
            for( i in it){
                if(i == '['){
                    flag = false
                    if(lyrics != ""){
                        listLyrics.add(lyrics)
                        lastAddType = LYRICS
                    }
                    Log.d("lyrics!l!",lyrics)
                    lyrics = ""
                    continue
                }else if (i == ']'){
                    flag = true
                    if(lastAddType == TIME){
                        listTime.removeAt(listTime.size - 1)
                    }
                    listTime.add(time.subSequence(0,5) as String?)
                    lastAddType = TIME
                    Log.d("lyrics!t!",time)
                    time = ""
                    continue
                }else if(i == '\n'){
                    Log.d("lyrics!ttttt!","sasdgf")
                    continue
                }
                if(flag){
                    lyrics += i
                }else{
                    time += i
                }
            }
        }
        PlayList.playList.storeData(listLyrics,listTime)
        notifyDataSetChanged()
    }
    fun changeData(lyric:ArrayList<String?>,time:ArrayList<String?>){
        listLyrics = lyric
        listTime = time
        notifyDataSetChanged()
    }
    inner class Lyrics(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lyricsTextView = itemView.findViewById<TextView>(R.id.lyrics_single)
        val timeTextView = itemView.findViewById<TextView>(R.id.lyrics_time)
    }
}