package com.example.eforkids.view.fragment.game

import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.eforkids.OnItemClickListener
import com.example.eforkids.adapter.SubContentAdapter
import com.example.eforkids.databinding.FragmentListQuizBinding
import com.example.eforkids.model.Content
import com.example.eforkids.model.SubContent
import com.example.eforkids.view.fragment.BaseFragment
import com.example.eforkids.viewmodel.EnglishVM

class SubContentFragment : BaseFragment<FragmentListQuizBinding>(), OnItemClickListener {
    private lateinit var englishVM: EnglishVM
    private lateinit var content: Content
    companion object{
        const val KEY_SEND_CONTENT: String = "KEY_SEND_DATA"
    }

    override fun initViews() {
        englishVM = ViewModelProvider(this)[EnglishVM::class.java]
        content = mData as Content
        binding.tvContent.text = content.name
        binding.ivBack.setOnClickListener {
            callback.backToPrevious()
        }



        val adapter = SubContentAdapter(mContext, this)
        val rcv = binding.rcvListQuiz
        rcv.adapter = adapter

        englishVM.readSubContentById(content.id).observe(viewLifecycleOwner, Observer {
            subContent -> adapter.setData(subContent)
        })
    }

    override fun initViewBinding(container: ViewGroup?): FragmentListQuizBinding {
        return FragmentListQuizBinding.inflate(layoutInflater, container, false)
    }

    override fun onItemClick(key: String, data: Any?) {
        val subContent: SubContent = data as SubContent
        if(subContent.status == 0){
            callback.showFragment(SubContentFragment::class.java,
                ItemGameFragment::class.java,
                arrayListOf(data, content as Any),
                true)
        } else{
            notify("You completed this, don't receive coins")
            callback.showFragment(SubContentFragment::class.java,
                ItemGameFragment::class.java,
                arrayListOf(data, content as Any),
                true)
        }
    }

}