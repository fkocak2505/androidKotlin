package com.androidlab.apptheme

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.drawable.DrawableCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener(View.OnClickListener {
            ThemeHelper.applyTheme(ThemeHelper.DARK_MODE)
        })

        setSupportActionBar(findViewById<View>(R.id.toolbar) as Toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        // This demonstrates how to programmatically tint a drawable
        val item = menu.findItem(R.id.action_more)
        val drawableWrap =
            DrawableCompat.wrap(item.icon).mutate()
        DrawableCompat.setTint(drawableWrap, ColorUtils.getThemeColor(this, R.attr.colorPrimary))
        item.icon = drawableWrap
        return true
    }
}
