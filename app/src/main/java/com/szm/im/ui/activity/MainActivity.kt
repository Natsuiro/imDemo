package com.szm.im.ui.activity

import com.hyphenate.EMConnectionListener
import com.hyphenate.EMError
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.szm.im.R
import com.szm.im.adapter.EMMessageListenerAdapter
import com.szm.im.factory.FragmentFactory
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.lang.RuntimeException

class MainActivity : BaseActivity() {
    //加载布局id
    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }
    //注册消息监听，如果收到消息就更新bottomBar的状态
    private val messageListener = object : EMMessageListenerAdapter() {
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            runOnUiThread {
                updateBottomBar()
            }
        }
    }

    override fun init() {
        super.init()
        //根据bottomBar的点击事件切换主页的fragment
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
        //注册监听器
        EMClient.getInstance().chatManager().addMessageListener(messageListener)

        //多处登录强制下线，这边就会断开连接
        EMClient.getInstance().addConnectionListener(object : EMConnectionListener{
            override fun onConnected() {

            }

            override fun onDisconnected(p0: Int) {
               if (p0 == EMError.USER_LOGIN_ANOTHER_DEVICE){
                   //jump to login
                   startActivity<LoginActivity>()//进入登录界面重新登录
                   toast("已在别处登录")
                   finish()
               }
            }

        })
    }

    override fun onResume() {
        super.onResume()
        updateBottomBar()//每次打开活动直到可以进行用户交互时获取底边栏消息状态
    }

    private fun updateBottomBar() {
        //init bar unreadCount
        val tabWithId = bottomBar.getTabWithId(R.id.tab_conversation)//获取id对应的tab实例
        //设置BadgeCount为未读消息的个数
        tabWithId.setBadgeCount(EMClient.getInstance().chatManager().unreadMessageCount)
    }

    override fun onDestroy() {
        super.onDestroy()
        //取消绑定监听
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)
    }

}
