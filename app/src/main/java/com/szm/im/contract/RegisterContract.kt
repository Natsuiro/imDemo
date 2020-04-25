package com.szm.im.contract

import com.szm.im.presenter.BasePresenter

interface RegisterContract{
    interface Presenter: BasePresenter {
        fun register(userName:String,password:String,confirmPassword:String)
    }
    interface View {
        fun onUserNameError()
        fun onPasswordError()
        fun onConfirmPasswordError()
        fun onStartRegister()
        fun onRegisterSuccess()
        fun onRegisterFailed(msg:String)
        fun onUserExist()
    }
}