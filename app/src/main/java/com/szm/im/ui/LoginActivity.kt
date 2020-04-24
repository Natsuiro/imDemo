package com.szm.im.ui

import android.view.KeyEvent
import android.widget.TextView
import com.szm.im.R
import com.szm.im.contract.LoginContract
import com.szm.im.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : BaseActivity(),LoginContract.View {

    private val presenter = LoginPresenter(this)
    override fun init() {
        super.init()
        login.setOnClickListener { login() }
        password.setOnEditorActionListener { _, _, _ ->
            login()
            true
        }
    }

    private fun login(){
        val userName = username.text.trim().toString()
        val password = password.text.trim().toString()
        presenter.login(userName,password)
    }

    override fun getLayoutResId(): Int =
        R.layout.activity_login

    override fun onUserNameError() {
        username.error = getString(R.string.invalid_username)
    }

    override fun onPasswordError() {
        password.error = getString(R.string.invalid_password)
    }

    override fun onStartLogin() {
        //弹出一个登录进度条
        showProgressDialog(getString(R.string.logging))
    }

    override fun onLoggedInSuccess() {
        //首先隐藏进度条
        //进入主界面
        //退出loginActivity
        dismissProgressDialog()
        startActivity<MainActivity>()
        finish()
    }

    override fun onLoggedInFailed() {
        //隐藏进度条
        dismissProgressDialog()
        toast(R.string.login_failed)
    }


}
