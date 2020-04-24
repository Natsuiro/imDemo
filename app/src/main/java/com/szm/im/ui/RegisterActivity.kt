package com.szm.im.ui
import com.szm.im.R
import com.szm.im.contract.RegisterContract
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseActivity(),RegisterContract.View{

    override fun getLayoutResId(): Int {
        return R.layout.activity_register
    }

    override fun onUserNameError() {
        username.error = getString(R.string.invalid_username)
    }

    override fun onPasswordError() {
        password.error = getString(R.string.invalid_password)
    }

    override fun onConfirmPassword() {
        confirm_password.error = getString(R.string.invalid_confirm_password)
    }

    override fun onStartRegister() {
        showProgressDialog(getString(R.string.registering))
    }

    override fun onRegisterSuccess() {
        dismissProgressDialog()
        finish()
    }

    override fun onRegisterFailed() {
        dismissProgressDialog()
        toast(R.string.register_fail)
    }
}
