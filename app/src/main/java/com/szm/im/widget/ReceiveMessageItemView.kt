package com.szm.im.widget

import android.content.Context
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

class ReceiveMessageItemView(context: Context?, attrs: AttributeSet? = null) :
    RelativeLayout(context, attrs) {
    fun bindView(emMessage: EMMessage) {
        updateTimeStamp(emMessage)
        updateMessage(emMessage)
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
        View.inflate(context, R.layout.receive_message_item,this)
    }

}