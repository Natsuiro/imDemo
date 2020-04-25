package com.szm.im.ui.activity

import android.os.Handler
import com.szm.im.R
import com.szm.im.contract.SplashContract
import com.szm.im.presenter.SplashPresenter
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity(),SplashContract.View {

    private val presenter = SplashPresenter(this)
    companion object{
        const val DELAY = 2000L
    }
    //when handler be used at first time,the Handler() behind lazy will be exec
    private val handler by lazy {
        //now i need a handler
        Handler()
    }

    override fun init() {
        super.init()
        presenter.checkLoginStatus()
    }

    //let the BaseActivity inflate this layout with id
    override fun getLayoutResId(): Int =
        R.layout.activity_splash

    //delay 2s then jump to login page
    override fun onNotLoggedIn() {
        handler.postDelayed({
            //jump to login
            startActivity<LoginActivity>()
            finish()
        }, DELAY)
    }
    //jump to main page
    override fun onLoggedIn() {
        startActivity<MainActivity>()
        finish()
    }

}
