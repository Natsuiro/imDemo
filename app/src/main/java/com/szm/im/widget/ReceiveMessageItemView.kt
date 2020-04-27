package com.szm.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.hyphenate.chat.EMMessage
import com.szm.im.R

class ReceiveMessageItemView(context: Context?, attrs: AttributeSet? = null) :
    RelativeLayout(context, attrs) {
    fun bindView(emMessage: EMMessage) {

    }

    init {
        View.inflate(context, R.layout.receive_message_item,this)
    }

}