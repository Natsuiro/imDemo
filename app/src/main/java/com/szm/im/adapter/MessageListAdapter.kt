package com.szm.im.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hyphenate.chat.EMMessage
import com.hyphenate.util.DateUtils
import com.szm.im.widget.ReceiveMessageItemView
import com.szm.im.widget.SendMessageItemView

class MessageListAdapter(val context:Context,val messages:MutableList<EMMessage>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    companion object{
        const val ITEM_TYPE_SEND_MESSAGE = 0
        const val ITEM_TYPE_RECEIVE_MESSAGE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ITEM_TYPE_SEND_MESSAGE){
            return SendMessageViewHolder(SendMessageItemView(context))
        }else{
            return ReceiveMessageViewHolder(ReceiveMessageItemView(context))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].direct() == EMMessage.Direct.SEND){
            ITEM_TYPE_SEND_MESSAGE
        }else{
            ITEM_TYPE_RECEIVE_MESSAGE
        }
    }
    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val showTimeStamp = isShowTimeStamp(position)


        if(getItemViewType(position) == ITEM_TYPE_SEND_MESSAGE){
            val sendMessageItemView = holder.itemView as SendMessageItemView
            sendMessageItemView.bindView(messages[position],showTimeStamp)
        }else{
            val receiveMessageItemView = holder.itemView as ReceiveMessageItemView
            receiveMessageItemView.bindView(messages[position],showTimeStamp)
        }
    }

    private fun isShowTimeStamp(position: Int): Boolean {
        //如果是第一条消息或者和前一条消息时间间隔比较长，才显示时间戳
        var showTimeStamp = true

        if (position>0){
            showTimeStamp = !DateUtils.isCloseEnough(messages[position].msgTime,messages[position-1].msgTime)
        }
        return showTimeStamp
    }

    class SendMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    class ReceiveMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }



}