package com.szm.im.presenter

import com.szm.im.contract.ChatContract

class ChatPresenter(val view:ChatContract.View) : ChatContract.Presenter {
    override fun sendMessage(contact: String, message: String) {

    }

}