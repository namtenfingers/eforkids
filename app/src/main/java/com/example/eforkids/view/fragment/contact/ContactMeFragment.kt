package com.example.eforkids.view.fragment.contact

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.example.eforkids.R
import com.example.eforkids.databinding.FragmentContactMeBinding
import com.example.eforkids.view.fragment.BaseFragment

class ContactMeFragment : BaseFragment<FragmentContactMeBinding>() {

    private lateinit var uri: Uri
    private lateinit var url: String
    override fun initViews() {
        setStatusBarColor(R.color.purple)
        binding.ivFacebook.setOnClickListener {
            goToFacebook()
        }
        binding.ivGoogle.setOnClickListener {
            goToGoogle()
        }
        binding.ivPhone.setOnClickListener {
            openPhone()
        }
    }

    private fun openPhone() {
        val i = Intent(Intent.ACTION_DIAL)
        i.data = Uri.parse("tel:"+ binding.tvPhone.text.toString())
        startActivity(i)
    }

    private fun goToGoogle() {
        url = "https://www.google.com/intl/vi/gmail/about/"
        uri = Uri.parse(url)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    private fun goToFacebook() {
        url = "https://www.facebook.com/chanxong.hup.5015/"
        uri = Uri.parse(url)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }


    override fun initViewBinding(container: ViewGroup?): FragmentContactMeBinding {
        return FragmentContactMeBinding.inflate(layoutInflater, container, false)
    }
}