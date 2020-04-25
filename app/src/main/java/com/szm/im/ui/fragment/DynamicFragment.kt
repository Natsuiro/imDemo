package com.szm.im.ui.fragment

import com.hyphenate.chat.EMClient
import com.szm.im.R
import kotlinx.android.synthetic.main.fragment_dynamic.*
import kotlinx.android.synthetic.main.header.*

class DynamicFragment : BaseFragment() {
    override fun getLayoutResId(): Int {
        return R.layout.fragment_dynamic
    }

    override fun init() {
        super.init()
        header_title.text = getString(R.string.dynamic)
        username.text = EMClient.getInstance().currentUser

        log_out.setOnClickListener {

        }

    }

}