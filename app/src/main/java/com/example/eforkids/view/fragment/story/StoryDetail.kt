package com.example.eforkids.view.fragment.story

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.view.ViewGroup
import com.example.eforkids.databinding.FragmentDetailStoryBinding
import com.example.eforkids.db.EnglishDB
import com.example.eforkids.model.SubType
import com.example.eforkids.view.fragment.BaseFragment

class StoryDetail : BaseFragment<FragmentDetailStoryBinding>() {
    private lateinit var listStory: ArrayList<SubType>
    override fun initViews() {

        listStory = arrayListOf()
        val story: SubType = mData as SubType



        binding.icBack.setOnClickListener {
            notify(String.format("%d", listStory.size))
        }
        binding.tvNameStory.text = story.name
        binding.tvDetail.text = story.detail
    }

    override fun initViewBinding(container: ViewGroup?): FragmentDetailStoryBinding {
        return FragmentDetailStoryBinding.inflate(layoutInflater, container, false)
    }
}