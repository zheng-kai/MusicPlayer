package com.example.a.musicplayer.songs.player

import android.content.ComponentName
import android.content.Context.BIND_AUTO_CREATE
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.*
import com.example.a.musicplayer.R
import com.example.a.musicplayer.R.id.seekbar
import com.example.a.musicplayer.songs.player.PlayList.Companion.playList
import com.example.a.musicplayer.songs.player.data.SongBean
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class PlayerUI : AppCompatActivity(), PlayerService.UI {
    private val presenter = Presenter(this)
    private val playList = PlayList.playList
    private lateinit var priorButton: Button
    private lateinit var nextButton: Button
    private lateinit var pauseOrPlayButton: Button
    private lateinit var image: ImageView
    private lateinit var seekbar: SeekBar
    private lateinit var totalTime: TextView
    private lateinit var currentTime: TextView
    private var myService: MyService? = null
    private var picUrl: String? = ""
    private var sCnn: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d("mmmbind", myService.toString())
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            myService = (service as MyService.MyBinder).getService()
            Log.d("emmmbind", myService.toString())
        }
    }
    val handler = Handler()
    val runnable = object : Runnable {
        override fun run() {
            var flag = true
            while (flag) {
                myService?.let {
                    totalTime.text = convertToTime(it.getDuration())
                    currentTime.text = convertToTime(it.getCurrentPosition())
                    seekbar.progress = it.getCurrentPosition()
                    seekbar.max = it.getDuration()
                    flag = false
                }
            }
            while (true) {
                handler.postDelayed(this, 1L)
                myService?.let {
                    currentTime.text = convertToTime(it.getCurrentPosition())
                    seekbar.progress = it.getCurrentPosition()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.player)
        init()
        setClick()
        picUrl = intent.getStringExtra("picUrl")
        playList.storeData(picUrl)
        loadImage(picUrl)
        presenter.getData(intent.getStringExtra("id"))
        handler.post(runnable)
    }

    override fun success(bean: SongBean) {
        val sIntent = Intent(this, MyService::class.java)
        sIntent.putExtra("url", bean.data[0].url)
        startService(sIntent)
        bindService(sIntent, sCnn, BIND_AUTO_CREATE)

    }

    override fun onError() {
        Toast.makeText(this, "请检查网络", Toast.LENGTH_LONG).show()
    }

    override fun loadImage(picUrl: String?) {
        Picasso.with(this)
            .load(picUrl)
            .fit()
            .into(image)
    }

    fun init() {
        playList.setUI(this)
        image = findViewById(R.id.image_song)
        priorButton = findViewById(R.id.prior)
        nextButton = findViewById(R.id.next)
        pauseOrPlayButton = findViewById(R.id.pause_play)
        seekbar = findViewById(R.id.seekbar)
        totalTime = findViewById(R.id.total_time)
        currentTime = findViewById(R.id.current_time)
    }

    fun setClick() {
        priorButton.setOnClickListener {
            myService?.playPrior()
            playList.playPrior()
        }
        nextButton.setOnClickListener {
            myService?.playNext()
            playList.playNext()
        }
        pauseOrPlayButton.setOnClickListener {
            myService?.let {
                if (it.isPlaying()) {
                    pauseOrPlayButton.setBackgroundResource(R.drawable.pause)
                } else {
                    pauseOrPlayButton.setBackgroundResource(R.drawable.play)
                }
                it.pauseOrPlay()
            }

        }
        seekbar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (fromUser) {
                        seekBar?.let {
                            myService?.seekTo(it.progress)
                        }
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }

            })
    }

    fun convertToTime(number: Int): String {
        val time = number / 100
        val minute = time / 60
        val second = time - minute * 60
        var totalTime = ""
        if (minute / 10 == 0) {//minute 个位
            totalTime += "0"
        }
        totalTime += "$minute:"
        if (second / 10 == 0) {
            totalTime += "0"
        }
        totalTime += "$second"
        return totalTime
    }
}