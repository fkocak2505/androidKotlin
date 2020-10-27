package com.onedio.androidside.mvp.view.dashboard.profileFragment.generalSetting.listAdapter

import android.graphics.*


class RoundedTransformation(private val radius: Float, private val margin: Float) :
    com.squareup.picasso.Transformation {

    override fun transform(source: Bitmap): Bitmap {
        val paint = Paint()
        paint.setAntiAlias(true)
        paint.setShader(
            BitmapShader(
                source, Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP
            )
        )

        val output = Bitmap.createBitmap(
            source.width, source.height,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(output)
        canvas.drawRoundRect(
            RectF(
                margin.toFloat(), margin.toFloat(), (source.width - margin).toFloat(),
                (source.height - margin).toFloat()
            ), radius, radius, paint
        )

        if (source != output) {
            source.recycle()
        }
        return output
    }

    override fun key(): String {
        return "rounded(r=$radius, m=$margin)"
    }
}