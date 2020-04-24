package com.szm.im.app

import android.app.Application
import android.os.Build
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions
import com.szm.im.BuildConfig

class IMApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        //IM SDK 的初始化
        val option = EMOptions()
        //添加好友不需要验证，false为改成需要验证
        //option.acceptInvitationAlways = false
        EMClient.getInstance().init(applicationContext,option)
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        //具体是否开启根据当前是不是debug模式
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG)
    }
}