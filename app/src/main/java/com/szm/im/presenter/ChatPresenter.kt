package com.szm.im.presenter

import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.szm.im.adapter.EMCallBackAdapter
import com.szm.im.contract.ChatContract
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ChatPresenter(val view:ChatContract.View) : ChatContract.Presenter {

    val messages = mutableListOf<EMMessage>()

    companion object{
        const val PAGE_SIZE = 10
    }

    override fun sendMessage(contact: String, message: String) {
        val emMessage = EMMessage.createTxtSendMessage(message,contact)
        messages.add(emMessage)
        view.onStartSendMessage()
        EMClient.getInstance().chatManager().sendMessage(emMessage)

        emMessage.setMessageStatusCallback(object : EMCallBackAdapter(){
            override fun onSuccess() {
                uiThread { view.onSendMessageSuccess() }
            }

            override fun onError(p0: Int, p1: String?) {
                uiThread { view.onSendMessageFailed() }
            }
        })


    }

    override fun addMessage(username: String, msg: MutableList<EMMessage>?) {
        msg?.let { messages.addAll(it) }
        //更新消息为已读消息
        //获取跟联系人的会话，标记会话的消息全部已读
        val conversation = EMClient.getInstance().chatManager().getConversation(username)
        conversation.markAllMessagesAsRead()
    }

    override fun loadMessages(username: String) {

        doAsync {
            val conversation = EMClient.getInstance().chatManager().getConversation(username)
            messages.addAll(conversation.allMessages)
            uiThread {
                view.onMessageLoaded()
            }
        }


    }

    override fun loadMoreMessages(username: String) {

        doAsync {
            val conversation = EMClient.getInstance().chatManager().getConversation(username)
            val startMsgId = messages[0].msgId
            val loadMoreMsgFromDB = conversation.loadMoreMsgFromDB(startMsgId, PAGE_SIZE)
            messages.addAll(0,loadMoreMsgFromDB)
            uiThread {
                view.onMoreMessagesLoaded(loadMoreMsgFromDB.size)
            }
        }



    }


}