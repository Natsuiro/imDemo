package com.szm.im.ui.activity

import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.szm.im.R
import com.szm.im.contract.ChatContract
import com.szm.im.presenter.ChatPresenter
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

class ChatActivity : BaseActivity() ,ChatContract.View{
    override fun getLayoutResId(): Int =
        R.layout.activity_chat

    val presenter = ChatPresenter(this)
    var username:String = ""

    override fun init() {
        super.init()
        initHeader()
        initEditText()

        send.setOnClickListener {
            sendMessage()
        }

    }

    private fun sendMessage() {
        hideSoftKeyBoard()
        val message = edit.text.trim().toString()
        presenter.sendMessage(username,message)
    }

    private fun initEditText() {
        edit.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                send.isEnabled = !s.isNullOrEmpty()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
        
        edit.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            sendMessage()
            true
        })
    }

    private fun initHeader() {
        back.visibility = View.VISIBLE
        back.setOnClickListener { finish() }

        //获取聊天的对象
        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        username = intent.getStringExtra("username")
        header_title.text = username


    }

    override fun onStartSendMessage() {
        //通知recyclerView刷新列表
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onSendMessageSuccess() {
        recyclerView.adapter?.notifyDataSetChanged()
        toast("send Message success")
        //清空编辑框
        edit.text.clear()

    }

    override fun onSendMessageFailed() {
        toast("send Message failed")
        recyclerView.adapter?.notifyDataSetChanged()
    }

}
