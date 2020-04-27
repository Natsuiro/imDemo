package com.szm.im.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Handler
import androidx.core.app.ActivityCompat
import com.szm.im.R
import com.szm.im.contract.SplashContract
import com.szm.im.presenter.SplashPresenter
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

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
        if (hasReadPhoneStatePermission()){
            presenter.checkLoginStatus()
        }else{
            applyReadPhoneStatePermission()
        }


    }

    private fun applyReadPhoneStatePermission() {
        val permissions = arrayOf(Manifest.permission.READ_PHONE_STATE)
        ActivityCompat.requestPermissions(this,permissions,0)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED){
            toast(R.string.permission_denied)
            finish()
        }else{
            presenter.checkLoginStatus()
        }


    }

    private fun hasReadPhoneStatePermission(): Boolean {

        val checkSelfPermission =
            ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
        return checkSelfPermission == PackageManager.PERMISSION_GRANTED
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
