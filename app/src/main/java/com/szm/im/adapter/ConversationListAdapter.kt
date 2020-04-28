package com.szm.im.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hyphenate.chat.EMConversation
import com.szm.im.widget.ConversationListItemView

class ConversationListAdapter(
    val context: Context,
    private val conversations: MutableList<EMConversation>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ConversationListViewHolder(ConversationListItemView(context))
    }

    override fun getItemCount(): Int = conversations.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    class ConversationListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}