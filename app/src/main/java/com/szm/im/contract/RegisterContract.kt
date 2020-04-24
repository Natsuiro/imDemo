package com.szm.im.contract

interface RegisterContract{
    interface Presenter:BasePresenter{
        fun register(userName:String,password:String,confirmPassword:String)
    }
    interface View {
        fun onUserNameError()
        fun onPasswordError()
        fun onConfirmPassword()
        fun onStartRegister()
        fun onRegisterSuccess()
        fun onRegisterFailed()
    }
}