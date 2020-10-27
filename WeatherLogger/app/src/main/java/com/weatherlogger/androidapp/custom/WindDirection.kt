package com.weatherlogger.androidapp.custom

//==============================================================================================
/**
 * Get wind degree and return Direction String..
 */
//==============================================================================================
enum class WindDirection {
    N, NNE, NE, ENE, E, ESE, SE, SSE, S, SSW, SW, WSW, W, WNW, NW, NNW;

    companion object {
        fun fromDegrees(deg: Double): WindDirection {
            val d = (deg / 22.5 + 0.5).toInt() % 16
            return values()[d]
        }
    }
}