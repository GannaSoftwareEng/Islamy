
package com.example.islmay

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

class SunriseView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val sunPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rayPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        sunPaint.color = Color.rgb(255, 215, 0)
        sunPaint.style = Paint.Style.FILL

        linePaint.color = Color.argb(130, 255, 255, 255)
        linePaint.strokeWidth = 2f
        linePaint.style = Paint.Style.STROKE

        rayPaint.color = Color.rgb(255, 220, 40)
        rayPaint.strokeWidth = 5f
        rayPaint.strokeCap = Paint.Cap.ROUND

        circlePaint.color = Color.argb(60, 255, 255, 255)
        circlePaint.style = Paint.Style.STROKE
        circlePaint.strokeWidth = 2f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height * 0.72f

        // Large circle around the sun
        canvas.drawCircle(
            centerX,
            centerY,
            55f,
            circlePaint
        )

        // Horizon line
        canvas.drawLine(
            5f,
            centerY,
            width - 5f,
            centerY,
            linePaint
        )

        // Small marks on horizon
        canvas.drawCircle(
            centerX - 60f,
            centerY,
            2.5f,
            linePaint
        )

        canvas.drawCircle(
            centerX + 60f,
            centerY,
            2.5f,
            linePaint
        )

        // Sun
        canvas.drawCircle(
            centerX,
            centerY,
            27f,
            sunPaint
        )

        // Sun rays
        val rayRadius = 40f

        for (i in 0 until 8) {

            val angle = Math.toRadians(
                i * 22.5 - 112.5
            )

            val startX =
                centerX + cos(angle).toFloat() * 34f

            val startY =
                centerY + sin(angle).toFloat() * 34f

            val endX =
                centerX + cos(angle).toFloat() * rayRadius

            val endY =
                centerY + sin(angle).toFloat() * rayRadius

            canvas.drawLine(
                startX,
                startY,
                endX,
                endY,
                rayPaint
            )
        }

        // Small yellow point at the top
        canvas.drawCircle(
            centerX + 18f,
            centerY - 55f,
            3f,
            sunPaint
        )
    }
}

