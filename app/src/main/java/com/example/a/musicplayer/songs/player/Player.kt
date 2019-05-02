package com.example.a.musicplayer.songs.player

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.a.musicplayer.R
import com.example.a.musicplayer.songs.player.data.SongBean
import com.example.a.musicplayer.songs.playlist.data.Playlist
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URI


class Player : AppCompatActivity(), PlayerService.UI {

    private val presenter = Presenter(this)
    private lateinit var priorButton: Button
    private lateinit var nextButton: Button
    private lateinit var pauseOrPlayButton: Button
    private lateinit var image: ImageView

    inner class serviceConnection : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.player)
        initID()
        Picasso.with(this)
            .load(intent.getStringExtra("picUrl"))
            .fit()
            .into(image)
        presenter.getData(intent.getStringExtra("id"))

    }

    override fun success(bean: SongBean) {
        val sIntent = Intent(this, MyService::class.java)
        sIntent.putExtra("url", bean.data[0].url)
        GlobalScope.launch {
            startService(sIntent)
        }
    }

    override fun onError() {
        Toast.makeText(this, "请检查网络", Toast.LENGTH_LONG).show()
    }


    fun initID() {
        image = findViewById(R.id.image_song)
        priorButton = findViewById(R.id.prior)
        nextButton = findViewById(R.id.next)
        pauseOrPlayButton = findViewById(R.id.pause_play)
    }
}