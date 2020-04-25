package com.szm.im.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.szm.im.R
import com.szm.im.factory.FragmentFactory
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NullPointerException
import java.lang.RuntimeException

class MainActivity : BaseActivity() {
    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        super.init()

        bottomBar.setOnTabSelectListener { tabId ->

            val fragment = FragmentFactory.instance.getFragment(tabId)
            if (fragment!=null){
                val beginTransaction = supportFragmentManager.beginTransaction()
                beginTransaction.replace(R.id.fragment_frame,fragment)
                beginTransaction.commit()
            }else{
                throw RuntimeException()
            }

        }

    }

}
