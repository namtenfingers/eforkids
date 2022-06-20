package com.example.eforkids.view.fragment.learn

import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.eforkids.OnItemClickListener
import com.example.eforkids.R
import com.example.eforkids.adapter.ContentLearnAdapter
import com.example.eforkids.databinding.FragmentMainLearnBinding
import com.example.eforkids.view.fragment.BaseFragment
import com.example.eforkids.viewmodel.EnglishVM


class MainLearnFragment : BaseFragment<FragmentMainLearnBinding>(), OnItemClickListener {
    private lateinit var englishVM: EnglishVM
    override fun initViews() {
        setStatusBarColor(R.color.sky)
        englishVM = ViewModelProvider(this)[EnglishVM::class.java]
        val adapter = ContentLearnAdapter(mContext, this)
        val rcv = binding.rcvLearnTopic
        rcv.adapter = adapter
        englishVM.readAllContent.observe(viewLifecycleOwner, Observer {
            content -> adapter.setData(content)
        })

    }

    override fun initViewBinding(container: ViewGroup?): FragmentMainLearnBinding {
        return FragmentMainLearnBinding.inflate(layoutInflater, container, false)
    }

    override fun onItemClick(key: String, data: Any?) {
        if(key == ContentLearnAdapter.KEY_SHOW_DETAIL){
            callback.showFragment(this::class.java, ShowDetailFragment::class.java, data, true)
        }

    }
}