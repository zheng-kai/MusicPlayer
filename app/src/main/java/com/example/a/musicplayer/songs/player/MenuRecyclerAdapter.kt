package com.example.a.musicplayer.songs.player

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.a.musicplayer.R
import java.util.zip.Inflater

class MenuRecyclerAdapter(val context:Context):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var list: ArrayList<String?> = ArrayList()
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return Song(LayoutInflater.from(context).inflate(R.layout.recycler_item_player_menu,null,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        if(p0 is Song){
            p0.songName.text = list[p1]
        }
    }
    fun addData(name:String?){
        if(!list.contains(name)){
            list.add(name)
            notifyDataSetChanged()
        }
    }
    inner class Song(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val songName = itemView.findViewById<TextView>(R.id.song_name)
//        val deleteSong = itemView.findViewById<Button>(R.id.delete)
    }
}