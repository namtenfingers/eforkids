package com.example.eforkids.view.fragment.learn

import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.eforkids.OnItemClickListener
import com.example.eforkids.adapter.SubContentLearnAdapter
import com.example.eforkids.databinding.FragmentDetailSubContentLearnBinding
import com.example.eforkids.view.fragment.BaseFragment
import com.example.eforkids.view.fragment.note.MainNoteFragment
import com.example.eforkids.viewmodel.EnglishVM
import java.util.*

class ShowDetailFragment : BaseFragment<FragmentDetailSubContentLearnBinding>(),
    OnItemClickListener {
    private lateinit var adapter: SubContentLearnAdapter
    private lateinit var englishVM: EnglishVM
    override fun initViews() {
        englishVM = ViewModelProvider(this)[EnglishVM::class.java]
        val subContentId: Int = mData as Int
        val vpg = binding.vpgSubContent
        adapter = SubContentLearnAdapter(mContext, this)
        vpg.adapter = adapter
        englishVM.readSubContentById(subContentId)
            .observe(viewLifecycleOwner, Observer { subContent ->
                adapter.setData(subContent)
            })


    }

    override fun initViewBinding(container: ViewGroup?): FragmentDetailSubContentLearnBinding {
        return FragmentDetailSubContentLearnBinding.inflate(layoutInflater, container, false)
    }

    override fun onItemClick(key: String, data: Any?) {
        if (key == SubContentLearnAdapter.KEY_NOTE_WORD) {
            callback.showFragment(this::class.java, MainNoteFragment::class.java, null, true)
            return
        }
    }

}

