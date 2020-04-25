package com.szm.im.ui.fragment

import com.hyphenate.chat.EMClient
import com.szm.im.R
import com.szm.im.adapter.EMCallBackAdapter
import com.szm.im.ui.activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_dynamic.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class DynamicFragment : BaseFragment() {
    override fun getLayoutResId(): Int {
        return R.layout.fragment_dynamic
    }

    override fun init() {
        super.init()
        header_title.text = getString(R.string.dynamic)
        username.text = EMClient.getInstance().currentUser

        log_out.setOnClickListener {
            logOut()
        }

    }

    private fun logOut(){
        EMClient.getInstance().logout(true,object : EMCallBackAdapter(){
            override fun onSuccess() {

                context?.runOnUiThread {
                    toast(R.string.log_out_success)
                    context?.startActivity<LoginActivity>()
                    activity?.finish()
                }

            }

            override fun onError(p0: Int, p1: String?) {
                //thread
                context?.runOnUiThread {
                    toast(R.string.log_out_failed)
                }
            }

        })
    }
}