package com.szm.im.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.szm.im.R
import kotlinx.android.synthetic.main.header.*

class AddFriendActivity : BaseActivity() {
    override fun getLayoutResId(): Int {
        return R.layout.activity_add_friend
    }

    override fun init() {
        header_title.text = getString(R.string.add_friend)
    }

}
