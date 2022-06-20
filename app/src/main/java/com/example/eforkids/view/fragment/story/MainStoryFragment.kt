package com.example.eforkids.view.fragment.story

import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.eforkids.OnItemClickListener
import com.example.eforkids.R
import com.example.eforkids.adapter.StoryTypeAdapter
import com.example.eforkids.databinding.FragmentMainStoryBinding
import com.example.eforkids.view.fragment.BaseFragment
import com.example.eforkids.view.fragment.MenuFragment
import com.example.eforkids.viewmodel.EnglishVM

class MainStoryFragment : BaseFragment<FragmentMainStoryBinding>(), OnItemClickListener {

    private lateinit var englishVM: EnglishVM
    override fun initViews() {
        setStatusBarColor(R.color.navy)
        englishVM = ViewModelProvider(this)[EnglishVM::class.java]
        binding.actionbarStoryHeader.ivBackMenu.setOnClickListener {
            callback.showFragment(
                MainStoryFragment::class.java,
                MenuFragment::class.java,
                null,
                true
            )
        }

        val adapter = StoryTypeAdapter(mContext, this)
        val rcv = binding.rcvStoryType
        rcv.adapter = adapter
        englishVM.readAllStoryType.observe(viewLifecycleOwner, Observer {
            storyType -> adapter.setData(storyType)
        })
    }


    override fun initViewBinding(container: ViewGroup?): FragmentMainStoryBinding {
        return FragmentMainStoryBinding.inflate(layoutInflater, container, false)
    }

    override fun onItemClick(key: String, data: Any?) {
        if(key == StoryTypeAdapter.KEY_OPEN_SUB){
            callback.showFragment(MainStoryFragment::class.java, SubTypeFragment::class.java, data, true)
        }
    }
}