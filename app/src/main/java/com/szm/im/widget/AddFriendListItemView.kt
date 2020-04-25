package com.szm.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.szm.im.R

class AddFriendListItemView(context: Context?, attrs: AttributeSet? = null) :
    RelativeLayout(context, attrs) {
    init {
        View.inflate(context, R.layout.add_friend_item,this)
    }


}