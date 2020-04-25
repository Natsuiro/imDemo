package com.szm.im.presenter

import android.webkit.WebView
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.a.e
import com.szm.im.contract.AddFriendContract

class AddFriendPresenter(val view : AddFriendContract.View) : AddFriendContract.Presenter{
    override fun search(key: String) {

        val query = BmobQuery<BmobUser>()
        query.addWhereContains("username",key)
            .addWhereNotEqualTo("username",EMClient.getInstance().currentUser)
        query.findObjects(object : FindListener<BmobUser>(){
            override fun done(p0: MutableList<BmobUser>?, e: BmobException?) {
                if (e == null){
                    view.onSearchSuccess()
                }else{
                    view.onSearchFailed()
                }
            }

        })
    }

}