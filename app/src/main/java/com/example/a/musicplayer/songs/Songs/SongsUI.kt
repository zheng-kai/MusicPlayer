package com.example.a.musicplayer.songs.Songs

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.example.a.musicplayer.R
import com.example.a.musicplayer.songs.Songs.Data.Track

class SongsUI : AppCompatActivity(), Service.UI {
    private var id = ""
    private val recyclerAdapter = SongsRecyclerViewAdapter(this)
    private lateinit var recyclerView: RecyclerView
    private var presenter = SongsPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.songs_detail)
        Log.d("UIStart","true")
        recyclerView = findViewById(R.id.recycler_detail)
        id = intent.getStringExtra("id")
        confRecyclerAdapter()
        presenter.getData(id)
    }

    fun confRecyclerAdapter() {
        val recyclerLayoutManager = LinearLayoutManager(this)
        recyclerLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = recyclerLayoutManager
        recyclerView.adapter = recyclerAdapter
    }

    override fun onError() {
        Toast.makeText(this, "检查网络", Toast.LENGTH_SHORT).show()
    }

    override fun success(list:List<Track>?) {
        recyclerAdapter.addData(list)
    }
}