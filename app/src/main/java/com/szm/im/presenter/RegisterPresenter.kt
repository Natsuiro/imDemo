package com.szm.im.presenter

import com.szm.im.contract.RegisterContract
import com.szm.im.extensions.isValidPassword
import com.szm.im.extensions.isValidUserName

class RegisterPresenter(val view:RegisterContract.View): RegisterContract.Presenter{
    override fun register(userName: String, password: String, confirmPassword: String) {
        //check username
        if (userName.isValidUserName()){
            //check password
            if (password.isValidPassword()){

                //check Confirm password
                if (confirmPassword == password){
                    //password equals to confirm password
                    view.onStartRegister()
                    //begin register



                }else view.onConfirmPasswordError()

            }else view.onPasswordError()

        }else view.onUserNameError()
    }

}