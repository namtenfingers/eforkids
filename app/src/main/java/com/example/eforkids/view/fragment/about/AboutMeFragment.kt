package com.example.eforkids.view.fragment.about

import android.view.ViewGroup
import com.example.eforkids.R
import com.example.eforkids.databinding.FragmentAboutMeBinding
import com.example.eforkids.view.fragment.BaseFragment

class AboutMeFragment : BaseFragment<FragmentAboutMeBinding>() {
    override fun initViews() {
        setStatusBarColor(R.color.green)
    }

    override fun initViewBinding(container: ViewGroup?): FragmentAboutMeBinding {
        return FragmentAboutMeBinding.inflate(layoutInflater, container, false)
    }
}