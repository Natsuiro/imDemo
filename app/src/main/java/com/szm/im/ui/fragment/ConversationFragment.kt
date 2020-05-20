package com.szm.im.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMConversation
import com.hyphenate.chat.EMMessage
import com.szm.im.R
import com.szm.im.adapter.ConversationListAdapter
import com.szm.im.adapter.EMMessageListenerAdapter
import kotlinx.android.synthetic.main.fragment_conversation.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ConversationFragment : BaseFragment() {

    //历史聊天列表
    private val conversations = mutableListOf<EMConversation>()
    //监听消息接收
    private val messageListener = object : EMMessageListenerAdapter() {
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            loadConversation()
        }
    }
    override fun getLayoutResId(): Int {
        return R.layout.fragment_conversation
    }

    override fun init() {
        super.init()
        header_title.text = getString(R.string.message)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ConversationListAdapter(context,conversations)
        }
        EMClient.getInstance().chatManager().addMessageListener(messageListener)

        //loadConversation() 稍后会在onResume中调用，这里不需要调用
    }
    //异步加载聊天记录
    private fun loadConversation() {

        doAsync {
            conversations.clear()
            val allConversations = EMClient.getInstance().chatManager().allConversations
            conversations.addAll(allConversations.values)
            uiThread {
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        loadConversation()
    }

    override fun onDestroy() {
        super.onDestroy()
        //取消监听
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)
    }

}