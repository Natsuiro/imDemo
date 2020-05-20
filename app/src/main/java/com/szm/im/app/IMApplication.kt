package com.szm.im.app

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import cn.bmob.v3.Bmob
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMOptions
import com.hyphenate.chat.EMTextMessageBody
import com.szm.im.BuildConfig
import com.szm.im.R
import com.szm.im.adapter.EMMessageListenerAdapter
import com.szm.im.ui.activity.ChatActivity

class IMApplication : Application() {

    companion object{
        lateinit var instance: IMApplication
    }

    private val messageListener = object : EMMessageListenerAdapter() {
        override fun onMessageReceived(msg: MutableList<EMMessage>?) {
            if (!isForeGround()){
                showNotification(msg)
            }
        }
    }

    private fun showNotification(msg: MutableList<EMMessage>?) {

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        msg?.forEach {
            var contentText = getString(R.string.not_text_message)

            if (it.type == EMMessage.Type.TXT){
                contentText = (it.body as EMTextMessageBody).message
            }
            val intent = Intent(this,ChatActivity::class.java)
            intent.putExtra("username",it.conversationId())
            //val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)

            val taskStackBuilder = TaskStackBuilder.create(this).addParentStack(ChatActivity::class.java).addNextIntent(intent)

            val pendingIntent = taskStackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT)
           val notification = Notification.Builder(this)
               .setContentTitle(it.userName)
               .setContentText(contentText)
               .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.icon_avatar))
               .setSmallIcon(R.drawable.icon_avatar)
               .setContentIntent(pendingIntent)
               .setAutoCancel(true)
               .notification
            manager.notify(1,notification)
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        //IM SDK 的初始化
        val option = EMOptions()
        //添加好友不需要验证，false为改成需要验证
        //option.acceptInvitationAlways = false
        EMClient.getInstance().init(applicationContext,option)
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        //具体是否开启根据当前是不是debug模式
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG)

        //bmob init
        Bmob.initialize(applicationContext,"60e66e7bd29aa29d93835e203d228efa")

        EMClient.getInstance().chatManager().addMessageListener(messageListener)
    }

    private fun isForeGround():Boolean{
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.runningAppProcesses.forEach {
            if (it.processName == packageName){
               return it.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
            }
        }
        return false
    }


}