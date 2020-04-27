package com.szm.im.widget

import android.content.Context
import android.database.DatabaseUtils
import android.graphics.drawable.AnimationDrawable
import android.util.AttributeSet
import android.view.View
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.RelativeLayout
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.util.DateUtils
import com.szm.im.R
import kotlinx.android.synthetic.main.send_message_item.view.*
import java.util.*

class SendMessageItemView(context: Context?, attrs: AttributeSet? = null) :
    RelativeLayout(context, attrs) {
    fun bindView(emMessage: EMMessage) {
        updateTimeStamp(emMessage)
        updateMessage(emMessage)
        updateProgress(emMessage)
    }

    private fun updateProgress(emMessage: EMMessage) {
        emMessage.status().let {
            when(it){
                EMMessage.Status.INPROGRESS -> {

                    val progress = this.findViewById<ImageView>(R.id.progress)
                    val rotateAnimation = RotateAnimation(0f,360f)
                    rotateAnimation.duration = 2000
                    rotateAnimation.repeatCount = RotateAnimation.INFINITE
                    progress.startAnimation(rotateAnimation)

                    progress.visibility = View.VISIBLE
                    progress.setImageResource(R.drawable.icon_refresh)

                }
                EMMessage.Status.SUCCESS ->{
                    progress.visibility = View.GONE
                }
                EMMessage.Status.FAIL ->{
                    progress.clearAnimation()
                    progress.visibility = View.VISIBLE
                    progress.setImageResource(R.mipmap.error)
                }
            }
        }
    }

    private fun updateMessage(emMessage: EMMessage) {
        if (emMessage.type == EMMessage.Type.TXT){
            message.text = (emMessage.body as EMTextMessageBody).message
        }else{
            message.text = context.getString(R.string.not_text_message)
        }
    }

    private fun updateTimeStamp(emMessage: EMMessage) {
        timestamp.text = DateUtils.getTimestampString(Date(emMessage.msgTime))
    }

    init {
        View.inflate(context, R.layout.send_message_item,this)
    }

}