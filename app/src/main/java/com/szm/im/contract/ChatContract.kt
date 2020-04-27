package com.szm.im.contract

import com.hyphenate.chat.EMMessage
import com.szm.im.presenter.BasePresenter

interface ChatContract {
    interface Presenter :BasePresenter{
        fun sendMessage(contact:String,message:String)
        fun addMessage(username: String, messages : MutableList<EMMessage>?)
    }

    interface View{

        fun onStartSendMessage()
        fun onSendMessageSuccess()
        fun onSendMessageFailed()
    }
}