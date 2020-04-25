package com.szm.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.szm.im.R
import com.szm.im.data.AddFriendItem
import kotlinx.android.synthetic.main.add_friend_item.view.*

class AddFriendListItemView(context: Context?, attrs: AttributeSet? = null) :
    RelativeLayout(context, attrs) {
    fun bindView(addFriendItem: AddFriendItem) {
        username.text = addFriendItem.username
        timestamp.text = addFriendItem.timestamp
    }

    init {
        View.inflate(context, R.layout.add_friend_item,this)
    }


}