package com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.storyListView.story

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_story.*
import com.onedio.androidside.R
import com.onedio.androidside.mvp.view.dashboard.homeFragment.widgets.storyListView.storyConfig.StoriesProgressView


@SuppressLint("Registered")
class StoryWatch : AppCompatActivity(), StoriesProgressView.StoriesListener {

    private var counter = 0
    private val resources = intArrayOf(
        R.drawable.sample1,
        R.drawable.sample2,
        R.drawable.sample3,
        R.drawable.sample4,
        R.drawable.sample5,
        R.drawable.sample6
    )

    private val durations = longArrayOf(500L, 1000L, 1500L, 4000L, 5000L, 1000)

    private var pressTime = 0L
    private var limit = 500L



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_story)

        supportActionBar?.hide()


        stories!!.setStoriesCount(PROGRESS_COUNT)
        stories!!.setStoryDuration(3000L)
        // or
        //storiesProgressView.setStoriesCountWithDurations(durations);
        stories!!.setStoriesListener(this)
        //storiesProgressView.startStories();
        counter = 0
        stories!!.startStories(counter)

        image!!.setImageResource(resources[counter])

        // bind reverse view
        reverse.setOnClickListener { stories!!.reverse() }
        reverse.setOnTouchListener(onTouchListener)

        // bind skip view
        skip.setOnClickListener { stories!!.skip() }
        skip.setOnTouchListener(onTouchListener)


    }

    private val onTouchListener = View.OnTouchListener { v, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                pressTime = System.currentTimeMillis()
                stories!!.pause()
                return@OnTouchListener false
            }
            MotionEvent.ACTION_UP -> {
                val now = System.currentTimeMillis()
                stories!!.resume()
                return@OnTouchListener limit < now - pressTime
            }
        }
        false
    }

    override fun onDestroy() {
        // Very important !
        stories!!.destroy()
        super.onDestroy()
    }

    override fun onNext() {
        image!!.setImageResource(resources[++counter])
    }

    override fun onPrev() {
        if (counter - 1 < 0) return
        image!!.setImageResource(resources[--counter])
    }

    override fun onComplete() {
        Toast.makeText(applicationContext, "Bitti.. :)", Toast.LENGTH_SHORT).show()

        //startActivity(Intent(getApplicationContext(), MainActivity::class.java))

    }

    companion object {
        private const val PROGRESS_COUNT = 6
    }
}
