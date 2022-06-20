package com.example.eforkids.view.fragment.game

import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.eforkids.OnItemClickListener
import com.example.eforkids.R
import com.example.eforkids.adapter.ContentAdapter
import com.example.eforkids.databinding.FragmentMainGameBinding
import com.example.eforkids.model.Content
import com.example.eforkids.view.fragment.BaseFragment
import com.example.eforkids.viewmodel.EnglishVM

class MainGameFragment : BaseFragment<FragmentMainGameBinding>(), OnItemClickListener {
    private lateinit var englishVM: EnglishVM
    override fun initViews() {


        setStatusBarColor(R.color.orange)
        englishVM = ViewModelProvider(this)[EnglishVM::class.java]

        binding.ivBack.setOnClickListener {
            callback.backToPrevious()
        }

        val adapter = ContentAdapter(mContext, this)
        val rcv = binding.rcvContent
        rcv.adapter = adapter

        englishVM.readAllContent.observe(viewLifecycleOwner, Observer {
            content -> adapter.setData(content)
        })

    }


    override fun initViewBinding(container: ViewGroup?): FragmentMainGameBinding {
        return FragmentMainGameBinding.inflate(layoutInflater, container, false)
    }

    override fun onItemClick(key: String, data: Any?) {
        callback.showFragment(MainGameFragment::class.java, SubContentFragment::class.java, data, true)
    }


}