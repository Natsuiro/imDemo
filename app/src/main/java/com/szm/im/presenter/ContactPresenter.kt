package com.szm.im.presenter

import android.util.Log
import com.hyphenate.EMValueCallBack
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import com.szm.im.contract.ContactContract
import com.szm.im.data.ContactListItem
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ContactPresenter(val view: ContactContract.View) : ContactContract.Presenter {

    val contactListItems = mutableListOf<ContactListItem>()

    override fun loadContacts() {
        //该方法是一个同步方法，执行时会阻塞线程，需要在子线程中执行

        doAsync {
            try {
                val userNames = EMClient.getInstance().contactManager().allContactsFromServer

                userNames.sortBy { it[0] }
                Log.d("contactPresenter",""+userNames.size)

                userNames.forEachIndexed{ index,value->
                    //普安段是否显示首字符
                    val showFirstLetter = index == 0 || value[0] != userNames[index-1][0]
                    val contactListItem = ContactListItem(value,value[0].toUpperCase())
                    contactListItems.add(contactListItem)
                }

                //然后切换回主线程反馈ui
                uiThread {
                    Log.d("contactPresenter","LOAD_SUCCESS")
                    view.onLoadContactsSuccess()
                }
            }catch (e:HyphenateException){
                e.printStackTrace()
                uiThread {
                    view.onLoadContactsFailed()
                }
            }


        }




    }

}