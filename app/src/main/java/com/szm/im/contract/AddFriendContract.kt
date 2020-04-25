package com.szm.im.contract

import com.szm.im.presenter.BasePresenter

interface AddFriendContract {

    interface Presenter : BasePresenter{
        fun search(key : String)
    }
    interface View {
        fun onSearchSuccess()
        fun onSearchFailed()

    }

}