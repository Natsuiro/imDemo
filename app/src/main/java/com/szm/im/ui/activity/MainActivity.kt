package com.szm.im.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.szm.im.R
import com.szm.im.adapter.EMMessageListenerAdapter
import com.szm.im.factory.FragmentFactory
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NullPointerException
import java.lang.RuntimeException

class MainActivity : BaseActivity() {
    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    private val messageListener = object : EMMessageListenerAdapter() {
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            runOnUiThread {
                updateBottomBar()
            }
        }
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
        EMClient.getInstance().chatManager().addMessageListener(messageListener)
    }

    override fun onResume() {
        super.onResume()
        updateBottomBar()
    }

    private fun updateBottomBar() {
        //init bar unreadCount
        val tabWithId = bottomBar.getTabWithId(R.id.tab_conversation)
        tabWithId.setBadgeCount(EMClient.getInstance().chatManager().unreadMessageCount)
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)
    }

}
