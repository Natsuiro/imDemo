package com.szm.im.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.szm.im.R
import com.szm.im.adapter.AddFriendListAdapter
import kotlinx.android.synthetic.main.activity_add_friend.*
import kotlinx.android.synthetic.main.header.*

class AddFriendActivity : BaseActivity() {
    override fun getLayoutResId(): Int {
        return R.layout.activity_add_friend
    }

    override fun init() {
        header_title.text = getString(R.string.add_friend)

        recycleView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = AddFriendListAdapter(context)
        }
    }

}
