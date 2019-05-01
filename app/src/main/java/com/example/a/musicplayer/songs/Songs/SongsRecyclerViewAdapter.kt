package com.example.a.musicplayer.songs.Songs

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.a.musicplayer.R
import com.example.a.musicplayer.songs.Songs.Data.Track
import java.util.zip.Inflater

class SongsRecyclerViewAdapter(var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val SONG_TYPE = 0
    private var list = ArrayList<Track>()
    fun addData(list: List<Track>?) {
        this.list = list as ArrayList<Track>
        Log.d("song_list",list.toString())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return Song(LayoutInflater.from(context).inflate(R.layout.recycler_item_song, p0, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        if(p0 is Song){
            p0.numberText.text = (p1 + 1).toString()
            p0.titleText.text = list[p1].name
            if(list[p1].alia.isNotEmpty()){
                p0.aliaTitle.text = list[p1].alia[0]
            }
            var subTitle = ""
            list[p1].ar.forEach{
                subTitle += it.name + "/"
            }
            subTitle = subTitle.substring(0,subTitle.length - 1) + "-" + list[p1].al.name
            p0.subTitleText.text = subTitle
        }
    }

    inner class Song(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText = itemView.findViewById<TextView>(R.id.title)
        val aliaTitle = itemView.findViewById<TextView>(R.id.alia_title)
        val subTitleText = itemView.findViewById<TextView>(R.id.subtitle)
        val numberText = itemView.findViewById<TextView>(R.id.count)
    }
}