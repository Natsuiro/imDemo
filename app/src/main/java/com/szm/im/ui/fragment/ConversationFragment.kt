package com.szm.im.ui.fragment

import com.szm.im.R
import kotlinx.android.synthetic.main.header.*

class ConversationFragment : BaseFragment() {
    override fun getLayoutResId(): Int {
        return R.layout.fragment_conversation
    }

    override fun init() {
        super.init()
        header_title.text = getString(R.string.message)
    }

}