package com.example.eforkids.view.fragment.story

import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.eforkids.OnItemClickListener
import com.example.eforkids.R
import com.example.eforkids.adapter.SubTypeAdapter
import com.example.eforkids.databinding.FragmentSubTypeListBinding
import com.example.eforkids.model.StoryType
import com.example.eforkids.view.fragment.BaseFragment
import com.example.eforkids.viewmodel.EnglishVM

class SubTypeFragment : BaseFragment<FragmentSubTypeListBinding>(), OnItemClickListener {

    private lateinit var englishVM: EnglishVM
    override fun initViews() {
        setStatusBarColor(R.color.navy)
        val typeStory: StoryType = mData as StoryType
        englishVM = ViewModelProvider(this)[EnglishVM::class.java]

        binding.actionbarStoryHeader.tvTypeName.text = typeStory.nameType
        binding.actionbarStoryHeader.ivBackMenu.setOnClickListener {
            callback.backToPrevious()
        }

        val adapter = SubTypeAdapter(mContext, this)
        val rcv = binding.rcvStorySubType
        rcv.adapter = adapter

        englishVM.readAllSubType(typeStory.id).observe(viewLifecycleOwner, Observer {
            subStory -> adapter.setData(subStory)
        })



    }

    override fun initViewBinding(container: ViewGroup?): FragmentSubTypeListBinding {
        return FragmentSubTypeListBinding.inflate(layoutInflater, container, false)
    }

    override fun onItemClick(key: String, data: Any?) {
        callback.showFragment(SubTypeFragment::class.java, StoryDetail::class.java, data, true)
    }
}