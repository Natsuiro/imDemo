package com.szm.im.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.szm.im.widget.ContactListItemView

class ContactListAdapter(private val context:Context):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ContactItemViewHolder(ContactListItemView(context))
    }

    override fun getItemCount(): Int = 30

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    class ContactItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {



    }
}