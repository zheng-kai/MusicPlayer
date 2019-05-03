package com.example.a.musicplayer.songs.player

import android.animation.ObjectAnimator
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.widget.*
import com.example.a.musicplayer.R
import com.example.a.musicplayer.songs.player.data.SongBean
import com.squareup.picasso.Picasso


class PlayerUI : AppCompatActivity(), PlayerService.UI {
    private val SEQUENTIAL_PLAY = 0
    private var TYPE = SEQUENTIAL_PLAY
    private val presenter = Presenter(this)
    private val playList = PlayList.playList
    private lateinit var priorButton: Button
    private lateinit var nextButton: Button
    private lateinit var pauseOrPlayButton: Button
    private lateinit var image: ImageView
    private lateinit var seekBar: SeekBar
    private lateinit var totalTime: TextView
    private lateinit var currentTime: TextView
    private var animator: ObjectAnimator? = null
    private var myService: MyService? = null
    private var picUrl: String? = ""
    private var sCnn: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            myService = (service as MyService.MyBinder).getService()
            if(playList.isEmpty()){
                myService = null
            }
            handler.post(runnable)
        }
    }
    val handler = Handler()
    val runnable = Runnable {
        Log.d("runnable!", "run!")
        myService?.let {
            totalTime.text = convertToTime(it.getDuration())
            currentTime.text = convertToTime(it.getCurrentPosition())
            seekBar.progress = it.getCurrentPosition()
            seekBar.max = it.getDuration()
        }
    }
    val runnable2 = object : Runnable {
        //seekBar 自动移动。当前时间改变
        override fun run() {
            myService?.let {
                currentTime.text = convertToTime(it.getCurrentPosition())
                seekBar.progress = it.getCurrentPosition()
            }
            handler.postDelayed(this, 1L)
        }
    }

    override fun onResume() {
        handler.post(runnable2)
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.player)
        init()
        setClick()
        picUrl = intent.getStringExtra("picUrl")
        presenter.getData(intent.getStringExtra("id"))
    }

    override fun success(bean: SongBean) {
        val sIntent = Intent(this, MyService::class.java)
        sIntent.putExtra("url", bean.data[0].url)
        startService(sIntent)
        bindService(sIntent, sCnn, BIND_AUTO_CREATE)
        loadImage(picUrl)
        playList.storeData(picUrl,animator)
    }
    override fun onNull(){
        val sIntent = Intent(this, MyService::class.java)
        sIntent.putExtra("url", "")
        startService(sIntent)
        bindService(sIntent, sCnn, BIND_AUTO_CREATE)

        playList.play()

        Toast.makeText(this,"抱歉，没有这首歌曲",Toast.LENGTH_SHORT).show()
    }
    override fun onError() {
        Toast.makeText(this, "请检查网络", Toast.LENGTH_LONG).show()
    }
    override fun loadImage() {
        Picasso.with(this)
            .load(R.drawable.label)
            .fit()
            .into(image)
        animator = ObjectAnimator.ofFloat(image,"rotation",0f,360f)
        animator?.let{
            it.duration = 40000
            it.repeatCount = Animation.INFINITE
            it.interpolator = LinearInterpolator()
            it.start()
        }
    }
    override fun loadImage(picUrl: String?) {
        Picasso.with(this)
            .load(picUrl)
            .fit()
            .into(image)
        animator = ObjectAnimator.ofFloat(image,"rotation",0f,360f)
        animator?.let{
            it.duration = 40000
            it.repeatCount = Animation.INFINITE
            it.interpolator = LinearInterpolator()
            it.start()
        }
    }

    private fun init() {
        playList.setUI(this)
        image = findViewById(R.id.image_song)
        priorButton = findViewById(R.id.prior)
        nextButton = findViewById(R.id.next)
        pauseOrPlayButton = findViewById(R.id.pause_play)
        seekBar = findViewById(R.id.seekbar)
        totalTime = findViewById(R.id.total_time)
        currentTime = findViewById(R.id.current_time)
    }

    private fun setClick() {
        priorButton.setOnClickListener {
            playPrior()
        }
        nextButton.setOnClickListener {
            playNext()
        }
        pauseOrPlayButton.setOnClickListener {
            myService?.let {
                if (it.isPlaying()) {
                    pauseOrPlayButton.setBackgroundResource(R.drawable.play)
                    animator?.pause()
                } else {
                    pauseOrPlayButton.setBackgroundResource(R.drawable.pause)
                    animator?.resume()
                }
                it.pauseOrPlay()
            }
        }
        seekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (fromUser) {
                        seekBar?.let {
                            myService?.seekTo(it.progress)
                            currentTime.text = convertToTime(progress)
                        }
                    }
                    myService?.let{
                        if(progress == it.getDuration()){
                            when (TYPE) {
                                SEQUENTIAL_PLAY -> {
                                    playNext()
                                }
                            }
                        }
                    }

                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            })
    }
    fun playPrior(){
        animator?.cancel()
        myService?.playPrior()
        playList.playPrior()
        pauseOrPlayButton.setBackgroundResource(R.drawable.pause)
        seekBar.progress = 0
        currentTime.text = convertToTime(0)
        totalTime.text = convertToTime(myService?.getDuration()as Int)
        animator?.start()
    }
    fun playNext(){
        animator?.cancel()
        myService?.playNext()
        playList.playNext()
        pauseOrPlayButton.setBackgroundResource(R.drawable.pause)
        seekBar.progress = 0
        currentTime.text = convertToTime(0)
        totalTime.text = convertToTime(myService?.getDuration()as Int)
        animator?.start()
    }
    fun convertToTime(number: Int): String {
        val time = number / 1000
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