package com.szm.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.szm.im.R

class ContactListItemView(context: Context?, attrs: AttributeSet?=null) :
    RelativeLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.contact_list_item,this)
    }

}