package com.szm.im.ui.activity

import android.app.ProgressDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import com.szm.im.R
//基类，对所有的活动进行抽取一些公共属性
abstract class BaseActivity : AppCompatActivity() {

    val inputMethodManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    val progressDialog by lazy {
        ProgressDialog(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        init()
    }

    /**
     * 初始化一些公共的功能，比如说摇一摇，子类也可以复写该方法完成自己的初始化
     */
    open fun init() {

    }

    /**
     * 子类必须实现该方法返回一个布局资源id
     * @return Int:布局id
     */
    abstract fun getLayoutResId(): Int

    fun showProgressDialog(msg:String){
        progressDialog.setMessage(msg)
        progressDialog.show()
    }

    fun dismissProgressDialog(){
        progressDialog.dismiss()
    }

    fun hideSoftKeyBoard(){
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken,0)
    }
}
