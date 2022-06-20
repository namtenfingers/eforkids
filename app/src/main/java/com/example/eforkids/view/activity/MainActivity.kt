package com.example.eforkids.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.eforkids.R
import com.example.eforkids.databinding.ActivityMainBinding
import com.example.eforkids.view.fragment.SplashFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun initViews() {
        showFragment(MainActivity::class.java, SplashFragment::class.java)
    }

    override fun initViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

}