package com.szm.im.ui.fragment

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyphenate.EMContactListener
import com.hyphenate.chat.EMClient
import com.szm.im.R
import com.szm.im.adapter.ContactListAdapter
import com.szm.im.adapter.EMContactListenerAdapter
import com.szm.im.contract.ContactContract
import com.szm.im.presenter.ContactPresenter
import com.szm.im.ui.activity.AddFriendActivity
import com.szm.im.widget.SlideBar
import kotlinx.android.synthetic.main.fragment_contact.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ContactFragment : BaseFragment(),ContactContract.View {

    val presenter = ContactPresenter(this)

    override fun getLayoutResId(): Int {
        return R.layout.fragment_contact
    }

    override fun init() {
        header_title.text = getString(R.string.contact)
        add.visibility = View.VISIBLE
        add.setOnClickListener {
            context!!.startActivity<AddFriendActivity>()
        }



        swipeRefreshLayout.apply {
            setColorSchemeColors(resources.getColor(R.color.im_blue))
            isRefreshing = true
            setOnRefreshListener {
                presenter.loadContacts()
            }
        }

        recycleView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ContactListAdapter(context,presenter.contactListItems)
        }

        EMClient.getInstance().contactManager().setContactListener(object :
            EMContactListenerAdapter() {

            override fun onContactDeleted(p0: String?) {

                //重新获取联系人的数据
                presenter.loadContacts()

            }

            override fun onContactAdded(p0: String?) {
                //重新获取联系人的数据
                presenter.loadContacts()
            }


        })

        presenter.loadContacts()


        slideBar.onSectionChangeListener = object : SlideBar.OnSectionChangeListener{
            override fun onSectionChange(firstLetter: String) {
                section.visibility = View.VISIBLE
                section.text = firstLetter
                val position = getPosition(firstLetter)

                Log.d("contact", "position:$position")

                if (position != -1){
                    recycleView.smoothScrollToPosition(position)
                }

            }

            override fun onSlideFinish() {
                section.visibility = View.GONE
            }

        }
    }

    private fun getPosition(firstLetter: String): Int =
       presenter.contactListItems.binarySearch { contactListItem ->
            contactListItem.firstLetter.minus(firstLetter[0])
        }


    override fun onLoadContactsSuccess() {
        swipeRefreshLayout.isRefreshing = false
        recycleView.adapter?.notifyDataSetChanged()
        context?.toast(R.string.load_contact_success)
    }

    override fun onLoadContactsFailed() {
        swipeRefreshLayout.isRefreshing = false
        context?.toast(R.string.load_contact_failed)
    }

}