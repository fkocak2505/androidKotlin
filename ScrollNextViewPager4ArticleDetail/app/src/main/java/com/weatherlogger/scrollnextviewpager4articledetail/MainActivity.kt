package com.weatherlogger.scrollnextviewpager4articledetail

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.weatherlogger.scrollnextviewpager4articledetail.array.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var lastPosition = 0

    private lateinit var toolBar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Logger.addLogAdapter(AndroidLogAdapter())

        prepareToolbar()

        val strOfDataModel = intent.getStringExtra("DATA_MODEL")

        /*val doppelgangerAdapter = DoppelgangerAdapter(this, 40)
        doppelgangerViewPager.adapter = doppelgangerAdapter*/


        val adapter = ViewPagerAdapter(
            this,
            supportFragmentManager,
            strOfDataModel!!
        )
        doppelgangerViewPager.adapter = adapter


        // Optional
        //doppelgangerViewPager.setPageTransformer(DepthPageTransformer())
        //doppelgangerViewPager.setPageTransformer(ZoomOutPageTransformer())
        //doppelgangerViewPager.registerOnPageChangeCallback(doppelgangerPageChangeCallback)

        doppelgangerViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                val editor = getSharedPreferences("PREFS_NAME", MODE_PRIVATE).edit()
                if(lastPosition > position){
                    lastPosition = position
                    editor.putBoolean("IS_RIGHT", false)
                    editor.apply()
                }else{
                    lastPosition = position
                    editor.putBoolean("IS_RIGHT", true)
                    editor.apply()
                }
            }
        })

    }

    private fun prepareToolbar() {
        toolBar = toolBar4ArticleDetail as Toolbar
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        toolBar.setNavigationIcon(R.drawable.ic_back)

        invalidateOptionsMenu()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.toolbar_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }
}
