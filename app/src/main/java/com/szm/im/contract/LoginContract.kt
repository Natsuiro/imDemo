package com.szm.im.contract

interface LoginContract {
    interface LoginPresenter : BasePresenter{
        fun login(userName:String,password:String)
    }
    interface View {
        fun onUserNameError()
        fun onPasswordError()
        fun onStartLogin()
        fun onLoggedInSuccess()
        fun onLoggedInFailed()
    }
}