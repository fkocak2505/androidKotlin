package com.weatherlogger.androidworkingtoolbarkotlin

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.deneme1.*
import java.util.*

class Deneme1 : AppCompatActivity() {

    private var isPlaying: Boolean = false
    //private lateinit var menuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.deneme1)

        setSupportActionBar(toolBar as Toolbar?)
        Objects.requireNonNull<ActionBar>(supportActionBar).setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        (toolBar as Toolbar?)?.setNavigationIcon(R.drawable.ic_chevron_left)

        toolBar.setOnClickListener {
            Toast.makeText(applicationContext, "Toolbar' a tıklandı", Toast.LENGTH_SHORT).show()
        }
    }

    private fun changeToolbar(){
        invalidateOptionsMenu()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        if (!isPlaying)
            menu.removeItem(R.id.action_search2)
        else
            menu.removeItem(R.id.action_search)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        super.onPrepareOptionsMenu(menu)

        if (!isPlaying)
            menu.findItem(R.id.action_search).setIcon(R.drawable.ic_chevron_left)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            R.id.action_search -> {
                //onBackPressed()
                isPlaying = true
            }
            R.id.action_search2 -> {
                //onBackPressed()
                isPlaying = false
            }
            R.id.action_like -> Toast.makeText(
                applicationContext,
                "Action Like",
                Toast.LENGTH_SHORT
            ).show()
        }

        changeToolbar()

        return true
    }
}