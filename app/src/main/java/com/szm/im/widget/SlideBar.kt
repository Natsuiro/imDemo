package com.szm.im.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.szm.im.R
import org.jetbrains.anko.sp

class SlideBar(context: Context?, attrs: AttributeSet? = null) : View(context, attrs) {

    private var sectionHeight:Float = 0.0f

    var paint = Paint()

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
    }

    init {
        paint.apply {
            color = resources.getColor(android.R.color.darker_gray)
            textSize = sp(12).toFloat()
            textAlign = Paint.Align.CENTER
        }
    }

    override fun onDraw(canvas: Canvas) {
        val x = width * 1.0f / 2
        var y = sectionHeight
        SECTIONS.forEach {
            canvas.drawText(it, x, y, paint)
            y += sectionHeight
        }

    }

}