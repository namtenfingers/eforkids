package com.example.eforkids.view.activity

import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.example.eforkids.OnActionCallback
import com.example.eforkids.R
import com.example.eforkids.view.fragment.BaseFragment


abstract class BaseActivity<VM : ViewBinding> : AppCompatActivity(), View.OnClickListener,
    OnActionCallback {

    protected lateinit var binding: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = initViewBinding()
        setContentView(binding.root)
        initViews()
    }

    fun setStatusBarColor(color: Int): Int {
        val window: Window = this.window
        window.statusBarColor = ContextCompat.getColor(this, color)
        return color
    }

    abstract fun initViews()

    abstract fun initViewBinding(): VM

    override fun onClick(v: View?) {
        v?.startAnimation(AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_fade_in))
        clickView(v)
    }

    private fun clickView(v: View?) {
        // do something
    }

    override fun showFragment(tag: Class<*>, screenTag: Class<*>, data: Any?, isBack: Boolean) {
        val clazz = Class.forName(screenTag.name)
        val fragment = clazz.getConstructor().newInstance() as BaseFragment<*>
        fragment.callback = this
        fragment.mData = data
        val tran = supportFragmentManager.beginTransaction()
        tran.setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)
        if (isBack) {
            tran.addToBackStack(tag.name)
        }
        tran.replace(R.id.fr_main, fragment, screenTag.name).commit()
    }

    override fun backToPrevious() {
        onBackPressed()
    }
}