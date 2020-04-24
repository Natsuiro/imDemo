package com.szm.im.presenter

import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.a.e
import com.hyphenate.exceptions.HyphenateException
import com.szm.im.contract.RegisterContract
import com.szm.im.extensions.isValidPassword
import com.szm.im.extensions.isValidUserName
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class RegisterPresenter(val view: RegisterContract.View) : RegisterContract.Presenter {
    override fun register(userName: String, password: String, confirmPassword: String) {
        //check username
        if (userName.isValidUserName()) {
            //check password
            if (password.isValidPassword()) {

                //check Confirm password
                if (confirmPassword == password) {
                    //password equals to confirm password
                    view.onStartRegister()
                    //begin register
                    registerBmob(userName, password)

                } else view.onConfirmPasswordError()

            } else view.onPasswordError()

        } else view.onUserNameError()
    }

    private fun registerBmob(userName: String, password: String) {
        val bu = BmobUser()
        bu.username = userName
        bu.setPassword(password)

        bu.signUp<BmobUser>(object : SaveListener<BmobUser>() {
            override fun done(s: BmobUser?, e: BmobException?) {
                if (e == null) {
                    //注册成功,转而到环信的用户注册
                    registerEaseMob(userName, password)
                } else {
                    //注册失败
                    view.onRegisterFailed("server busy")
                }
            }
        })
    }

    private fun registerEaseMob(userName: String, password: String) {
        //subThread
        doAsync {
            try {
                //success
                EMClient.getInstance().createAccount(userName, password)
                uiThread { view.onRegisterSuccess() }
            }catch (e:HyphenateException){
                //failed
                e.printStackTrace()
                uiThread { view.onRegisterFailed("server busy") }
            }
        }


    }

}