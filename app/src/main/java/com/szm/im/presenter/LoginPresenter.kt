package com.szm.im.presenter

import com.szm.im.contract.LoginContract
import com.szm.im.extensions.isValidPassword
import com.szm.im.extensions.isValidUserName

class LoginPresenter(private val view:LoginContract.View) : LoginContract.Presenter{

    override fun login(userName: String, password: String) {
        if (userName.isValidUserName()){
            //用户名合法，继续校验password
            if (password.isValidPassword()){
                //密码合法
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

    }

}