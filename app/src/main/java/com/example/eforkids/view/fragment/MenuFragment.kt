package com.example.eforkids.view.fragment

import android.view.ViewGroup
import com.example.eforkids.R
import com.example.eforkids.databinding.FragmentMenuBinding
import com.example.eforkids.view.fragment.about.AboutMeFragment
import com.example.eforkids.view.fragment.contact.ContactMeFragment
import com.example.eforkids.view.fragment.game.MainGameFragment
import com.example.eforkids.view.fragment.learn.MainLearnFragment
import com.example.eforkids.view.fragment.note.MainNoteFragment
import com.example.eforkids.view.fragment.story.MainStoryFragment

class MenuFragment : BaseFragment<FragmentMenuBinding>() {
    override fun initViews() {
        setStatusBarColor(R.color.white)
        binding.feature1.setOnClickListener{
            callback.showFragment(MenuFragment::class.java, MainLearnFragment::class.java, null, true)
        }
        binding.feature2.setOnClickListener{
            callback.showFragment(MenuFragment::class.java, MainGameFragment::class.java, null, true)
        }
        binding.feature3.setOnClickListener{
            callback.showFragment(MenuFragment::class.java, MainNoteFragment::class.java, null, true)
        }
        binding.feature4.setOnClickListener{
            callback.showFragment(MenuFragment::class.java, MainStoryFragment::class.java, null, true)
        }
        binding.feature5.setOnClickListener{
            callback.showFragment(MenuFragment::class.java, AboutMeFragment::class.java, null, true)
        }
        binding.feature6.setOnClickListener{
            callback.showFragment(MenuFragment::class.java, ContactMeFragment::class.java, null, true)
        }
    }

    override fun initViewBinding(container: ViewGroup?): FragmentMenuBinding {
        return FragmentMenuBinding.inflate(layoutInflater, container, false)
    }
}