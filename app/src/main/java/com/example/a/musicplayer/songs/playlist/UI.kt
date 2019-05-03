package com.example.a.musicplayer.songs.playlist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.example.a.musicplayer.R
import com.example.a.musicplayer.songs.playlist.data.Playlist
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UI : AppCompatActivity(), Service.PLUI {
    private var userID = 0
    private var nickname = ""
    private val presenter = Presenter(this)
    private lateinit var headPhoto:ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var toolbar: Toolbar
    private var recyclerAdapter = RecyclerViewAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.playlist)
        toolbar = findViewById(R.id.toolbar_playlist)
        setSupportActionBar(toolbar)
        headPhoto = toolbar.findViewById(R.id.head_photo)
        Picasso.with(this)
            .load(intent.getStringExtra("headPhoto"))
            .fit()
            .into(headPhoto)
        val intent = intent
        userID = intent.getIntExtra("userID", 0)
        nickname = intent.getStringExtra("nickname")
        recyclerView = findViewById(R.id.recyclerview)
        confRecyclerAdapter()
        presenter.getData(userID)
    }

    fun confRecyclerAdapter() {
        val recyclerLayoutManager = LinearLayoutManager(this)
        recyclerLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = recyclerLayoutManager
        recyclerView.adapter = recyclerAdapter
    }

    override fun success(list : List<Playlist?>?) {
        recyclerAdapter.addData(list,nickname)
    }

    override fun onError() {
        Toast.makeText(this, "检查网络", Toast.LENGTH_SHORT).show()
    }
}