package com.szm.im.contract

import com.szm.im.presenter.BasePresenter

interface ContactContract {
    interface Presenter : BasePresenter {
        fun loadContacts()
    }
    interface View {
        fun onLoadContactsSuccess()
        fun onLoadContactsFailed()
    }
}