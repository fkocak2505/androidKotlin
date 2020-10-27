package com.example.scaleimageview

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView


class ImageScaleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ImageView(context, attrs, defStyle) {

    private var mMatrixType = MatrixCropType.TOP_CENTER // default

    private enum class MatrixCropType(private val mValue: Int) {
        TOP_CENTER(0),
        BOTTOM_CENTER(1);


        companion object {

            fun fromValue(value: Int): MatrixCropType {
                for (matrixCropType in values()) {
                    if (matrixCropType.mValue == value) {
                        return matrixCropType
                    }
                }

                // default
                return TOP_CENTER
            }
        }
    }

    init {

        // get attributes
        if (attrs != null) {
            val a =
                context.theme.obtainStyledAttributes(attrs, R.styleable.ImageScaleView, 0, 0)
            try {
                mMatrixType =
                    MatrixCropType.fromValue(a.getInteger(R.styleable.ImageScaleView_matrixType, 0))
            } finally {
                a.recycle()
            }
        }
    }

    override fun setFrame(
        frameLeft: Int,
        frameTop: Int,
        frameRight: Int,
        frameBottom: Int
    ): Boolean {

        val drawable = drawable
        if (drawable != null) {

            val frameWidth = (frameRight - frameLeft).toFloat()
            val frameHeight = (frameBottom - frameTop).toFloat()

            val originalImageWidth = getDrawable().intrinsicWidth.toFloat()
            val originalImageHeight = getDrawable().intrinsicHeight.toFloat()

            var usedScaleFactor = 1f

            if (frameWidth > originalImageWidth || frameHeight > originalImageHeight) {
                // If frame is bigger than image
                // => Crop it, keep aspect ratio and position it at the bottom
                // and
                // center horizontally
                val fitHorizontallyScaleFactor = frameWidth / originalImageWidth
                val fitVerticallyScaleFactor = frameHeight / originalImageHeight

                usedScaleFactor = Math.max(fitHorizontallyScaleFactor, fitVerticallyScaleFactor)
            }

            val newImageWidth = originalImageWidth * usedScaleFactor
            val newImageHeight = originalImageHeight * usedScaleFactor

            val matrix = imageMatrix
            matrix.setScale(usedScaleFactor, usedScaleFactor, 0.toFloat(), 0.toFloat())

            when (mMatrixType) {
                MatrixCropType.TOP_CENTER -> matrix.postTranslate(
                    (frameWidth - newImageWidth) / 2,
                    0.toFloat()
                )
                MatrixCropType.BOTTOM_CENTER -> matrix.postTranslate(
                    (frameWidth - newImageWidth) / 2,
                    frameHeight - newImageHeight
                )

                else -> {
                }
            }

            imageMatrix = matrix
        }
        return super.setFrame(frameLeft, frameTop, frameRight, frameBottom)
    }

}