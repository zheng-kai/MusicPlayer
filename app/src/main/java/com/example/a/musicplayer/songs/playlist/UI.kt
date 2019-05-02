package com.example.a.musicplayer.songs.playlist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.example.a.musicplayer.R
import com.example.a.musicplayer.songs.player.MyService
import com.example.a.musicplayer.songs.playlist.data.Playlist

class UI : AppCompatActivity(), Service.PLUI {
    private var userID = 0
    private var nickname = ""
    private var notClicked = true
    private val presenter = Presenter(this)
    private lateinit var recyclerView: RecyclerView
    private var recyclerAdapter = RecyclerViewAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.songs)
        val intent = intent
        userID = intent.getIntExtra("userID", 0)
        nickname = intent.getStringExtra("nickname")
        Log.d("userID",userID.toString())
        recyclerView = findViewById(R.id.recyclerview)
        confRecyclerAdapter()
        if(notClicked) {
            notClicked = false
            presenter.getData(userID)
        }
    }

    fun confRecyclerAdapter() {
        val recyclerLayoutManager = LinearLayoutManager(this)
        recyclerLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = recyclerLayoutManager
        recyclerView.adapter = recyclerAdapter
    }

    override fun success(list : List<Playlist?>?) {
        notClicked = true
        recyclerAdapter.addData(list,nickname)
    }

    override fun onError() {
        notClicked = true
        Toast.makeText(this, "检查网络", Toast.LENGTH_SHORT).show()
    }
}