package com.example.eforkids.view.fragment.note

import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.eforkids.OnItemClickListener
import com.example.eforkids.R
import com.example.eforkids.adapter.WordAdapter
import com.example.eforkids.databinding.FragmentNoteWordBinding
import com.example.eforkids.model.Topic
import com.example.eforkids.view.fragment.BaseFragment
import com.example.eforkids.viewmodel.EnglishVM

class NoteWordFragment : BaseFragment<FragmentNoteWordBinding>(), OnItemClickListener {
    private lateinit var englishVM: EnglishVM
    override fun initViews() {
        val topic: Topic = mData as Topic
        englishVM = ViewModelProvider(this)[EnglishVM::class.java]

        setStatusBarColor(R.color.green2)
        binding.ivAddWord.setOnClickListener {
            callback.showFragment(NoteWordFragment::class.java, AddWordFragment::class.java, topic, true)
        }

        binding.actionbarWord.ivBack.setOnClickListener {
            callback.backToPrevious()
        }

        binding.actionbarWord.tvNameTopic.text = topic.title

        val adapter = WordAdapter(mContext, this)
        val rcv = binding.rcvWord
        rcv.adapter = adapter

        englishVM.readWordByTopicId(topic.id).observe(viewLifecycleOwner, Observer {
            word -> adapter.setData(word)
        })
    }

    override fun initViewBinding(container: ViewGroup?): FragmentNoteWordBinding {
        return FragmentNoteWordBinding.inflate(layoutInflater, container, false)
    }

    override fun onItemClick(key: String, data: Any?) {
        if(key == WordAdapter.KEY_SPEAK_WORD){
            callback.showFragment(NoteWordFragment::class.java, WordSpeakerFragment::class.java, data, true)
        } else if(key == WordAdapter.KEY_CHANGE_WORD){
            callback.showFragment(NoteWordFragment::class.java, SelectionWordFragment::class.java, data, true)
        }
    }
}