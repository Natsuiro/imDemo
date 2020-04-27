package com.szm.im.contract

import com.hyphenate.chat.EMMessage
import com.szm.im.presenter.BasePresenter

interface ChatContract {
    interface Presenter :BasePresenter{
        fun sendMessage(contact:String,message:String)
        fun addMessage(username: String, messages : MutableList<EMMessage>?)
        fun loadMessages(username: String)
        fun loadMoreMessages(username: String)
    }

    interface View{

        fun onStartSendMessage()
        fun onSendMessageSuccess()
        fun onSendMessageFailed()
        fun onMessageLoaded()
        fun onMoreMessagesLoaded(size: Int)


    }
}