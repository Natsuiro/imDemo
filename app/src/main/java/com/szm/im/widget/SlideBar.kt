package com.szm.im.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.szm.im.R
import org.jetbrains.anko.sp

class SlideBar(context: Context?, attrs: AttributeSet? = null) : View(context, attrs) {

    private var sectionHeight:Float = 0.0f

    var paint = Paint()
    var baseline = 0f
    companion object {
        private val SECTIONS = arrayOf(
            "A", "B", "C", "D", "E"
            , "F", "G", "H", "I", "J"
            , "K", "L", "M", "N", "O"
            , "P", "Q", "R", "S", "T"
            , "U", "V", "W", "X", "Y", "Z"
        )
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        sectionHeight = h  * 1.0f / SECTIONS.size
        val fontMetrics = paint.fontMetrics
        val textHeight = fontMetrics.descent - fontMetrics.ascent
        baseline = sectionHeight/2 + (textHeight / 2 - fontMetrics.descent)
    }

    init {
        paint.apply {
            color = resources.getColor(android.R.color.holo_blue_dark)
            textSize = sp(12).toFloat()
            textAlign = Paint.Align.CENTER
        }
    }

    override fun onDraw(canvas: Canvas) {
        val x = width * 1.0f / 2
        var y = baseline
        SECTIONS.forEach {
            canvas.drawText(it, x, y, paint)
            y += sectionHeight
        }

    }


    override fun onTouchEvent(event: MotionEvent): Boolean {

        when(event.action){
            MotionEvent.ACTION_DOWN ->
                setBackgroundResource(R.drawable.bg_slide_bar)
            MotionEvent.ACTION_UP ->
                setBackgroundColor(Color.TRANSPARENT)
//            MotionEvent.ACTION_MOVE ->
        }
        return true
    }

}