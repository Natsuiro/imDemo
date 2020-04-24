package com.szm.im.ui

import android.os.Handler
import com.szm.im.R
import com.szm.im.contract.SplashContract
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity(),SplashContract.View {

    companion object{
        const val DELAY = 2000L
    }
    //when handler be used at first time,the Handler() behind lazy will be exec
    private val handler by lazy {
        Handler()
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
