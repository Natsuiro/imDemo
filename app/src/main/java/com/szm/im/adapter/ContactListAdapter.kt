package com.szm.im.adapter

import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.hyphenate.chat.EMClient
import com.szm.im.R
import com.szm.im.data.ContactListItem
import com.szm.im.ui.activity.ChatActivity
import com.szm.im.widget.ContactListItemView
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ContactListAdapter(private val context:Context,private val contactListItems: MutableList<ContactListItem>):RecyclerView.Adapter<ContactListAdapter.ContactItemViewHolder>() {

    private val tag = "ContactListAdapter"

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
        val userName = contactListItems[position].username
        contactListItemView.setOnClickListener { context.startActivity<ChatActivity>("username" to userName) }

        contactListItemView.setOnLongClickListener {

            val message = "really delete $userName ?"
            AlertDialog.Builder(context)
                .setTitle(R.string.delete_friend_title)
                .setMessage(message)
                .setNegativeButton(R.string.action_cancel,null)
                .setPositiveButton(R.string.action_confirm) { _, _ ->
                    deleteFriend(userName)
                }
                .show()
            true

        }

    }

    private fun deleteFriend(userName: String) {
        Log.d(tag,"delete friend!")
        //doAsync
        EMClient.getInstance().contactManager().aysncDeleteContact(userName,object :
            EMCallBackAdapter() {
            override fun onSuccess() {
                context.runOnUiThread {
                    toast(R.string.delete_friend_success)
                }
            }

            override fun onError(p0: Int, p1: String?) {
                context.runOnUiThread {
                    toast(R.string.delete_friend_failed)
                }
            }

        })

    }

    class ContactItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }
}