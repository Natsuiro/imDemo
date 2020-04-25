package com.szm.im.ui.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.szm.im.R
import com.szm.im.adapter.ContactListAdapter
import kotlinx.android.synthetic.main.fragment_contact.*
import kotlinx.android.synthetic.main.header.*

class ContactFragment : BaseFragment() {
    override fun getLayoutResId(): Int {
        return R.layout.fragment_contact
    }

    override fun init() {
        super.init()
        header_title.text = getString(R.string.contact)
        add.visibility = View.VISIBLE

        swipeRefreshLayout.apply {
            setColorSchemeColors(resources.getColor(R.color.im_blue))
            isRefreshing = true
        }

        recycleView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ContactListAdapter(context)
        }

    }

}