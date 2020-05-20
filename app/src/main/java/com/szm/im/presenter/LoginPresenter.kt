package com.szm.im.presenter

import android.util.Log
import com.hyphenate.EMCallBack
import com.hyphenate.chat.EMClient
import com.szm.im.adapter.EMCallBackAdapter
import com.szm.im.contract.LoginContract
import com.szm.im.extensions.isValidPassword
import com.szm.im.extensions.isValidUserName

class LoginPresenter(private val view:LoginContract.View) : LoginContract.Presenter{

    override fun login(userName: String, password: String) {
        if (userName.isValidUserName()){
            //用户名合法，继续校验password
            if (password.isValidPassword()){
                //密码合法,通知ui反馈用户
                view.onStartLogin()
                loginEaseMob(userName,password)
            }else{
                view.onPasswordError()
            }
        }else{
            view.onUserNameError()
        }
    }

    /**
     * 登录到环信服务器
     */
    private fun loginEaseMob(userName: String, password: String) {
        EMClient.getInstance().login(userName,password,object : EMCallBackAdapter() {
            //在子线程回调
            override fun onSuccess() {
                //在主线程中通知view层
                EMClient.getInstance().groupManager().loadAllGroups()

                EMClient.getInstance().chatManager().loadAllConversations()

                Log.d("login","login success!")
                //切换到主线程
                uiThread { view.onLoggedInSuccess() }
            }

            override fun onError(p0: Int, p1: String?) {
                //切换到主线程
                uiThread { view.onLoggedInFailed() }
            }

        })
    }

}