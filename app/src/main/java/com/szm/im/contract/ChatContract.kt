package com.szm.im.contract

import com.szm.im.presenter.BasePresenter

interface ChatContract {
    interface Presenter :BasePresenter{
        fun sendMessage(contact:String,message:String)
    }
    interface View{

        fun onStartSendMessage()
        fun onSendMessageSuccess()
        fun onSendMessageFailed()
    }
}