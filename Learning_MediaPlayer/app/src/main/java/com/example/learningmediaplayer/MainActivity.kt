package com.example.learningmediaplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    var totalTime: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // declare variable
        val play = findViewById<ImageView>(R.id.playImg)
        val pause = findViewById<ImageView>(R.id.pauseImg)
        val stop = findViewById<ImageView>(R.id.stopImg)
        val seekBar = findViewById<SeekBar>(R.id.seekBar)


        mediaPlayer = MediaPlayer.create(this, R.raw.motivation)
        mediaPlayer.setVolume(1f, 1f)
        totalTime = mediaPlayer.duration

        play.setOnClickListener {
            mediaPlayer.start()
        }
        pause.setOnClickListener {
            mediaPlayer.pause()
        }
        stop.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer.reset()
            mediaPlayer.release()
        }

        // when user change the time stamp of music
        seekBar.max = totalTime
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })

        // change the seekbar position based on the music
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    seekBar.progress = mediaPlayer.currentPosition
                    handler.postDelayed(this, 1000)
                } catch (exception: java.lang.Exception) {
                    seekBar.progress = 0
                }
            }
        }, 0)

    }
}