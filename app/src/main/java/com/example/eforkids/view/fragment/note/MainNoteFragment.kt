package com.example.eforkids.view.fragment.note

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.eforkids.OnItemClickListener
import com.example.eforkids.R
import com.example.eforkids.adapter.TopicAdapter
import com.example.eforkids.databinding.FragmentMainNoteBinding
import com.example.eforkids.view.fragment.BaseFragment
import com.example.eforkids.view.fragment.MenuFragment
import com.example.eforkids.viewmodel.EnglishVM

class MainNoteFragment : BaseFragment<FragmentMainNoteBinding>(), OnItemClickListener {
    private lateinit var englishVM: EnglishVM
    override fun initViews() {

        setStatusBarColor(R.color.pink)
        englishVM = ViewModelProvider(this)[EnglishVM::class.java]

        binding.ivAddTopic.setOnClickListener {
            callback.showFragment(MainNoteFragment::class.java, AddTopicFragment::class.java, null, true)
        }
        binding.actionbarTopic.ivBack.setOnClickListener {
            callback.showFragment(MainNoteFragment::class.java, MenuFragment::class.java)
        }
        showListTopic()
    }

    private fun showListTopic() {
        val adapter = TopicAdapter(mContext, this)
        val recyclerView = binding.rcvTopic
        recyclerView.adapter = adapter
        englishVM.readAllTopic.observe(viewLifecycleOwner, Observer { topic ->
            adapter.setData(topic)
        })
    }


    override fun initViewBinding(container: ViewGroup?): FragmentMainNoteBinding {
        return FragmentMainNoteBinding.inflate(layoutInflater, container, false)
    }

    override fun onItemClick(key: String, data: Any?) {
        if (key == TopicAdapter.KEY_CHANGE_TOPIC) {
            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("Hello")
            alertDialog.setMessage("Bạn muốn ...")
            alertDialog.setPositiveButton("Thoát") { _: DialogInterface, _: Int ->
                // dismiss alert dialog
            }
            alertDialog.setNegativeButton("Sửa") { _: DialogInterface, _: Int ->
                callback.showFragment(
                    MainNoteFragment::class.java,
                    SelectionTopicFragment::class.java,
                    data,
                    true
                )
            }
            alertDialog.show()
        } else if (key == TopicAdapter.KEY_OPEN_NOTE_WORD) {
            callback.showFragment(MainNoteFragment::class.java, NoteWordFragment::class.java, data, true)
        }
    }
}