package com.weatherlogger.androidapp.custom

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.TransitionDrawable
import android.os.Handler
import android.view.View
import androidx.core.content.ContextCompat
import java.util.*

//==============================================================================================
/**
 * GradientBackground Color.. Like Instagram Login screen..
 */
//==============================================================================================

class GradientBackgroundPainter(private val target: View, private val drawables: IntArray) {

    private val random: Random = Random()
    private val handler: Handler = Handler()
    private val context: Context = target.context.applicationContext

    private fun animate(firstDrawable: Int, secondDrawable: Int, duration: Int) {
        var secondDrawable = secondDrawable
        if (secondDrawable >= drawables.size) {
            secondDrawable = 0
        }
        val first = ContextCompat.getDrawable(context, drawables[firstDrawable])
        val second = ContextCompat.getDrawable(context, drawables[secondDrawable])

        val transitionDrawable = TransitionDrawable(arrayOf<Drawable>(first!!, second!!))

        target.setBackgroundDrawable(transitionDrawable)

        transitionDrawable.isCrossFadeEnabled = false

        transitionDrawable.startTransition(duration)

        val localSecondDrawable = secondDrawable
        handler.postDelayed(Runnable {
            animate(
                localSecondDrawable,
                localSecondDrawable + 1,
                randInt(MIN, MAX)
            )
        }, duration.toLong())
    }

    fun start() {
        val duration = randInt(MIN, MAX)
        animate(0, 1, duration)
    }

    fun stop() {
        handler.removeCallbacksAndMessages(null)
    }

    private fun randInt(min: Int, max: Int): Int {
        return random.nextInt(max - min + 1) + min
    }

    companion object {

        private val MIN = 4000
        private val MAX = 5000
    }
}