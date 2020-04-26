package com.szm.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.hyphenate.chat.EMClient
import com.szm.im.R
import com.szm.im.adapter.EMCallBackAdapter
import com.szm.im.data.AddFriendItem
import kotlinx.android.synthetic.main.add_friend_item.view.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast

class AddFriendListItemView(context: Context?, attrs: AttributeSet? = null) :
    RelativeLayout(context, attrs) {
    fun bindView(addFriendItem: AddFriendItem) {

        if(addFriendItem.hasAdded){
            add.isEnabled = false
            add.text = context.getString(R.string.has_add)
        }else{
            add.isEnabled = true
            add.text = context.getString(R.string.not_add)
        }
        username.text = addFriendItem.username
        timestamp.text = addFriendItem.timestamp

        add.setOnClickListener {
            addFriend(addFriendItem.username)
        }
    }

    private fun addFriend(username: String) {
        EMClient.getInstance().contactManager().aysncAddContact(username,null,object :
            EMCallBackAdapter() {
            override fun onSuccess() {
                context.runOnUiThread { toast("send add friend success") }
            }

            override fun onError(p0: Int, p1: String?) {
                context.runOnUiThread { toast("send add friend failed") }
            }
        })
    }

    init {
        View.inflate(context, R.layout.add_friend_item,this)
    }


}