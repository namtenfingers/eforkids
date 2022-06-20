package com.example.eforkids.view.fragment.game

import android.content.Intent
import android.net.Uri
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.eforkids.databinding.FragmentShowStatusBinding
import com.example.eforkids.model.SubContent
import com.example.eforkids.view.fragment.BaseFragment

class ShowStatusContent : BaseFragment<FragmentShowStatusBinding>() {
    private lateinit var subContent: SubContent
    private lateinit var urlWiki: String
    override fun initViews() {
        subContent = mData as SubContent
        urlWiki = "https://en.wikipedia.org/wiki/${subContent.name}"

        binding.tvNameSubContent.text = subContent.name
        Glide.with(mContext).load(subContent.imagePath).into(binding.ivSubContent)

        binding.btnMore.setOnClickListener{
            val uri = Uri.parse(urlWiki)
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }

        binding.btnBack.setOnClickListener {
            callback.backToPrevious()
        }

    }

    override fun initViewBinding(container: ViewGroup?): FragmentShowStatusBinding {
        return FragmentShowStatusBinding.inflate(layoutInflater, container, false)
    }
}