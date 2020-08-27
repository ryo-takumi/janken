package com.example.janken

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.os.Handler
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity(), View.OnClickListener  {

    lateinit var  timer: Timer
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonpa.setOnClickListener(this)
        buttongu.setOnClickListener(this)
        buttonchoki.setOnClickListener(this)

    }

    override fun onResume() {
        super.onResume()
        var f = 0


        handler = Handler()
        timer = Timer()
        timer.schedule(2000,300,{runOnUiThread{
            f++

            if (f == 1) {
                imageView.setImageResource(R.drawable.gu)
            }else if(f == 2){
                imageView.setImageResource(R.drawable.choki)
            }else{
                imageView.setImageResource(R.drawable.pa)
            }

            if (f == 3){
                f = 0
            }

        }})
    }

    override fun onPause() {
        super.onPause()
        timer.cancel()
    }

    override fun onClick(v: View?) {

        val n = Random().nextInt(3)

        if (n==0){
            imageViewResult.setImageResource(R.drawable.gu)
        }else if (n==1){
            imageViewResult.setImageResource(R.drawable.choki)
        }else{
            imageViewResult.setImageResource(R.drawable.pa)
        }
        textViewResult.text = getString(R.string.pon)


        when(v?.id) {
            R.id.buttongu
                    -> buttongu.setBackgroundResource(R.drawable.gu2)

            R.id.buttonchoki
            -> buttonchoki.setBackgroundResource(R.drawable.choki2)

            R.id.buttonpa
            -> buttonpa.setBackgroundResource(R.drawable.pa2)
        }

        imageViewResult.visibility = View.VISIBLE
        imageView.visibility = View.INVISIBLE
        handler.postDelayed(Runnable {
            when(v?.id){
                R.id.buttongu
                -> {
                    buttongu.setBackgroundResource(R.drawable.gu)
                    if (n == 0) {
                        textViewResult.text = getString(R.string.draw)
                    } else if (n == 1) {
                        textViewResult.text = getString(R.string.win)
                    } else {
                        textViewResult.text = getString(R.string.loose)
                    }
                }

                R.id.buttonchoki
                -> {
                    buttonchoki.setBackgroundResource(R.drawable.choki)
                    if (n==0){
                        textViewResult.text = getString(R.string.loose)
                    }else if (n==1){
                        textViewResult.text = getString(R.string.draw)
                    }else {
                        textViewResult.text = getString(R.string.win)
                    }
                }

                R.id.buttonpa
                -> {
                    buttonpa.setBackgroundResource(R.drawable.pa)
                    if (n == 0) {
                        textViewResult.text = getString(R.string.win)
                    } else if (n == 1) {
                        textViewResult.text = getString(R.string.loose)
                    } else {
                        textViewResult.text = getString(R.string.draw)
                    }
                }

            }
        },1000)

        handler.postDelayed(Runnable {
            buttongu.isEnabled = true
            buttonchoki.isEnabled = true
            buttonpa.isEnabled = true

            textViewResult.text = getString(R.string.jannkenn)
            imageViewResult.visibility = View.INVISIBLE
            imageView.visibility = View.VISIBLE
        },2000)
    }
    }
