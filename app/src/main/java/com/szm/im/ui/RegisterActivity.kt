package com.szm.im.ui
import android.view.KeyEvent
import android.widget.TextView
import com.szm.im.R
import com.szm.im.contract.RegisterContract
import com.szm.im.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseActivity(),RegisterContract.View{

    val presenter = RegisterPresenter(this)

    override fun init() {
        super.init()
        register.setOnClickListener {
            register()
        }
        confirm_password.setOnEditorActionListener { _, _, _ ->
            register()
            true
        }
    }

    private fun register(){
        hideSoftKeyBoard()
        val username = username.text.trim().toString()
        val password = password.text.trim().toString()
        val confirmPassword = confirm_password.text.trim().toString()

        presenter.register(username,password,confirmPassword)
    }
    override fun getLayoutResId(): Int {
        return R.layout.activity_register
    }

    override fun onUserNameError() {
        username.error = getString(R.string.invalid_username)
    }

    override fun onPasswordError() {
        password.error = getString(R.string.invalid_password)
    }

    override fun onConfirmPasswordError() {
        confirm_password.error = getString(R.string.invalid_confirm_password)
    }

    override fun onStartRegister() {
        showProgressDialog(getString(R.string.registering))
    }

    override fun onRegisterSuccess() {
        dismissProgressDialog()
        toast(R.string.register_success)
        finish()
    }

    override fun onRegisterFailed(msg:String) {
        dismissProgressDialog()
        toast(getString(R.string.register_fail)+":$msg")
    }

    override fun onUserExist() {
        dismissProgressDialog()
        toast(R.string.user_exist)
    }
}
