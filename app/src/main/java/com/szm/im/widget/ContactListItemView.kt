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

        if(contactListItem.showFirstLetter){
            firstLetter.visibility = View.VISIBLE
            firstLetter.text = contactListItem.firstLetter.toString()
        }else{
            firstLetter.visibility = View.GONE
        }
        username.text = contactListItem.username
    }

    init {
        View.inflate(context, R.layout.contact_list_item,this)
    }

}