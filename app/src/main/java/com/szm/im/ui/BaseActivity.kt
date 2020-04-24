package com.szm.im.ui

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.szm.im.R
//基类，对所有的活动进行抽取一些公共属性
abstract class BaseActivity : AppCompatActivity() {


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
}
