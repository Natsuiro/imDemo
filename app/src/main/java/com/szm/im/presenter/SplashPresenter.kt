package com.szm.im.presenter

import com.hyphenate.chat.EMClient
import com.szm.im.contract.SplashContract

class SplashPresenter(private val view:SplashContract.View):SplashContract.Presenter{
    //获取登录状态
    override fun checkLoginStatus() {
        if (isLoggedIn())
            view.onLoggedIn()
        else
            view.onNotLoggedIn()

    }


    private fun isLoggedIn(): Boolean =
        //Model层的工作由第三方sdk完成，这里是在presenter中处理具体的业务逻辑
        EMClient.getInstance().isConnected && EMClient.getInstance().isLoggedInBefore
}