package com.example.eforkids.view.fragment

import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import com.example.eforkids.R
import com.example.eforkids.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    override fun initViews() {
        setStatusBarColor(R.color.white)
        val handler = Handler(Looper.myLooper()!!)
        handler.postDelayed({
            callback.showFragment(SplashFragment::class.java, MenuFragment::class.java)
        },1500)
    }

    override fun initViewBinding(container: ViewGroup?): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(layoutInflater, container, false)
    }


}