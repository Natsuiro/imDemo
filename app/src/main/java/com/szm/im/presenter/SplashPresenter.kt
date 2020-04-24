package com.szm.im.presenter

import com.szm.im.contract.SplashContract

class SplashPresenter(private val view:SplashContract.View):SplashContract.Presenter{

    override fun checkLoginStatus() {
        if (isLoggedIn())
            view.onLoggedIn()
        else
            view.onNotLoggedIn()

    }

    private fun isLoggedIn(): Boolean {
        return false
    }

}