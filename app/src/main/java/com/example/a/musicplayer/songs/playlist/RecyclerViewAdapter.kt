package com.example.a.musicplayer.songs.playlist

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.a.musicplayer.R
import com.example.a.musicplayer.songs.playlist.data.Playlist
import com.example.a.musicplayer.songs.Songs.UI
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TITLE_TYPE = 0
    private val SONGS_TYPE = 1
    //    private var myFlag = 1
//    private var subFlag = 1
    private var myListCount = 0
    private var subCount = 0
    private var nickname = ""
    private var list: ArrayList<Playlist?> = ArrayList()
    fun addData(list: List<Playlist?>?,nickname : String) {
        this.list = list?.toMutableList() as ArrayList<Playlist?>
        list.forEach { i ->
            Log.d("subCount", subCount.toString())
            Log.d("myCount", myListCount.toString())
            i?.let {
                if (it.creator.nickname != nickname) {
                    subCount++
                } else {
                    myListCount++
                }
            }
        }


        this.list.add(0, null)
        this.list.add(myListCount + 1, null)
        for(i in list){
            Log.d("myList",i.toString())
        }
        this.nickname = nickname
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return if (p1 == TITLE_TYPE) {
            val itemView = LayoutInflater.from(context).inflate(R.layout.recycler_item_list_title, p0, false)
            ListTitle(context, itemView)
        } else {
            val itemView = LayoutInflater.from(context).inflate(R.layout.recycler_item_playlist, p0, false)
            SongsList(context, itemView)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0 || position == myListCount + 1) TITLE_TYPE else SONGS_TYPE
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        if (p0 is ListTitle) {
            if (p1 < myListCount) {
                p0.textView.text = "我的歌单"
//                p0.getItemView().setOnClickListener {
//                    myFlag = if(myFlag == 1) 0
//                    else
//                        1
//                }
            } else if (p1 == myListCount + 1) {
                p0.textView.text = "收藏的歌单"
//                p0.getItemView().setOnClickListener{
//                    subFlag = if(subFlag == 1) 0
//                    else
//                        1
//                }
            }
        } else if (p0 is SongsList) {
            Picasso.with(context).load(list[p1]?.coverImgUrl)
                .fit()
                .into(p0.imageVIew)
            p0.textView.text = list[p1]?.name
            var str = "${list[p1]?.trackCount}首"
            if(list[p1]?.creator?.nickname != nickname){
                str += " by ${list[p1]?.creator?.nickname}"
            }
            p0.subtextView.text = str
        }
    }

    inner class SongsList(context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.text_list)
        val imageVIew = itemView.findViewById<ImageView>(R.id.imagelist)
        val subtextView = itemView.findViewById<TextView>(R.id.subtext_list)

        init {
            itemView.setOnClickListener {
                val intent = Intent(context, UI::class.java)
                context.startActivity(intent)
            }
        }
    }

    inner class ListTitle(context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.title)
//        fun getItemView() : View{
//            return itemView
//        }
    }
}