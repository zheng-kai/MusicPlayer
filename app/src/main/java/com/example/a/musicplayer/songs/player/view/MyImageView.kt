package com.example.a.musicplayer.songs.player.view

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import android.util.Log
import kotlin.math.max
import kotlin.math.min
import android.graphics.Shader
import android.graphics.BitmapShader



class MyImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
    AppCompatImageView(context, attrs, defStyle) {

    private var mWidth = 0f
    private var mHeight = 0f
    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.d("myView!", "measure")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = measuredWidth.toFloat()
        mHeight = measuredHeight.toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        Log.d("myView!", "draw")

        val drawable = drawable
        if (drawable is BitmapDrawable) {
            paint.shader = bitmapShade(drawable)
            canvas?.drawCircle(
                mWidth / 2, mHeight / 2,
                min(mWidth / 2, mHeight / 2), paint
            )
            return
        }
        super.onDraw(canvas)
    }

    fun bitmapShade(drawable: BitmapDrawable): BitmapShader {
        val bitmap = drawable.bitmap
        val bitmapShader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        val scale = max(mWidth / bitmap.width, mHeight / bitmap.height)
        matrix.setScale(scale, scale)
        bitmapShader.setLocalMatrix(matrix)
        return bitmapShader
    }
}