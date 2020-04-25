package com.szm.im.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.szm.im.data.ContactListItem
import com.szm.im.widget.ContactListItemView

class ContactListAdapter(private val context:Context,private val contactListItems: MutableList<ContactListItem>):RecyclerView.Adapter<ContactListAdapter.ContactItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactItemViewHolder {
        return ContactItemViewHolder(ContactListItemView(context))
    }

    override fun getItemCount(): Int {
        Log.d("adapter",""+contactListItems.size)
        return contactListItems.size
    }

    override fun onBindViewHolder(holder: ContactItemViewHolder, position: Int) {
        val contactListItemView = holder.itemView as ContactListItemView
        contactListItemView.bindView(contactListItems[position])
    }

    class ContactItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }
}