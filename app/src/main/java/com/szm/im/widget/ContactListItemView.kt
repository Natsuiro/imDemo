package com.szm.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.szm.im.R
import com.szm.im.data.ContactListItem
import kotlinx.android.synthetic.main.contact_list_item.view.*

class ContactListItemView(context: Context?, attrs: AttributeSet?=null) :
    RelativeLayout(context, attrs) {
    fun bindView(contactListItem: ContactListItem) {
        firstLetter.text = contactListItem.firstLetter.toString()
        username.text = contactListItem.username
    }

    init {
        View.inflate(context, R.layout.contact_list_item,this)
    }

}