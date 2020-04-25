package com.szm.im.presenter

import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import com.szm.im.contract.ContactContract
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ContactPresenter(val view: ContactContract.View) : ContactContract.Presenter {
    override fun loadContacts() {
        doAsync {
            try {
                val userNames = EMClient.getInstance().contactManager().allContactsFromServer
                uiThread {
                    view.onLoadContactsSuccess()
                }
            } catch (e: HyphenateException) {
                view.onLoadContactsFailed()
            }

        }
    }

}