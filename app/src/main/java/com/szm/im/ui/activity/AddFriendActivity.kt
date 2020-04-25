package com.szm.im.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.szm.im.R
import com.szm.im.adapter.AddFriendListAdapter
import com.szm.im.contract.AddFriendContract
import com.szm.im.presenter.AddFriendPresenter
import kotlinx.android.synthetic.main.activity_add_friend.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

class AddFriendActivity : BaseActivity(),AddFriendContract.View {
    override fun getLayoutResId(): Int {
        return R.layout.activity_add_friend
    }

    val presenter = AddFriendPresenter(this)

    override fun init() {
        header_title.text = getString(R.string.add_friend)

        recycleView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = AddFriendListAdapter(context)
        }

        search.setOnClickListener {
            search()
        }
        username.setOnEditorActionListener { _, _, _ ->
            search()
            true
        }
    }

    private fun search(){
        hideSoftKeyBoard()
        showProgressDialog(getString(R.string.searching))
        val key = username.text.trim().toString()
        presenter.search(key)
    }

    override fun onSearchSuccess() {
        dismissProgressDialog()
        toast(R.string.search_success)
        recycleView.adapter?.notifyDataSetChanged()
    }

    override fun onSearchFailed() {
        dismissProgressDialog()
        toast(R.string.search_failed)
    }

}
