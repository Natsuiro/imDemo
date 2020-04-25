package com.szm.im.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.szm.im.data.AddFriendItem
import com.szm.im.widget.AddFriendListItemView

class AddFriendListAdapter(val context: Context, private val addFriendItems: MutableList<AddFriendItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AddFriendListItemViewHolder(AddFriendListItemView(context))
    }

    override fun getItemCount(): Int = addFriendItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val addFriendItem = holder.itemView as AddFriendListItemView
        addFriendItem.bindView(addFriendItems[position])
    }

    class AddFriendListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}