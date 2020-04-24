package com.szm.im.presenter

import com.hyphenate.chat.EMClient
import com.szm.im.contract.SplashContract

class SplashPresenter(private val view:SplashContract.View):SplashContract.Presenter{

    override fun checkLoginStatus() {
        if (isLoggedIn())
            view.onLoggedIn()
        else
            view.onNotLoggedIn()

    }

    //是否登录到环信的服务器
    //注册并创建应用
//    下载SDK
//    SDK的导入
//    SDK的初始化
    private fun isLoggedIn(): Boolean =
        //Model层的工作由第三方sdk完成，这里是在presenter中处理具体的业务逻辑
        EMClient.getInstance().isConnected && EMClient.getInstance().isLoggedInBefore


}