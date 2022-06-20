package com.example.eforkids.view.fragment.note

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.text.Selection.setSelection
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.eforkids.databinding.FragmentSelectionWordBinding
import com.example.eforkids.model.Word
import com.example.eforkids.view.fragment.BaseFragment
import com.example.eforkids.viewmodel.EnglishVM

class SelectionWordFragment : BaseFragment<FragmentSelectionWordBinding>() {
    private lateinit var englishVM: EnglishVM
    private var uri: Uri? = null
    private var imagePath: String? = null
    override fun initViews() {
        englishVM = ViewModelProvider(this)[EnglishVM::class.java]
        setSelectionWord()

        binding.ivSearch.setOnClickListener {
            if (binding.edtUpdateWord.text.toString().isEmpty()) {
                notify("Nhập thông tin")
                return@setOnClickListener
            }
            val url =
                "https://www.google.com/search?hl=en&site=imghp&tbm=isch&source=hp&q=${binding.edtUpdateWord.text}"
            val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            mContext.startActivity(i)
        }

        binding.ivBack.setOnClickListener {
            callback.backToPrevious()
        }

        binding.ivUpdateWord.setOnClickListener {
            openGallery()
        }
    }

    private fun openGallery() {
        val photoPicker = Intent(Intent.ACTION_PICK)
        photoPicker.type = "image/*"
        startActivityForResult(photoPicker, 101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                uri = data.data!!
                imagePath = uri.toString()
                Glide.with(mContext).load(imagePath).centerInside().into(binding.ivUpdateWord)
            }
        }
    }

    private fun setSelectionWord() {
        val w: Word = mData as Word
        imagePath = w.imagePath

        binding.edtUpdateWord.setText(w.word)
        binding.edtUpdateMean.setText(w.mean)
        Glide.with(mContext).load(w.imagePath).centerInside().into(binding.ivUpdateWord)


        binding.btnUpdate.setOnClickListener {
            val topicIdForWord = w.topicId
            val word = binding.edtUpdateWord.text.toString()
            val mean = binding.edtUpdateMean.text.toString()
            val wordId = w.wordId
            val wordImage = imagePath.toString()
            if (word == w.word && mean == w.mean && wordImage == w.imagePath) {
                notify("Change word's information")
                return@setOnClickListener
            } else {
                val updateWord = Word(topicIdForWord, wordId, word, mean, imagePath.toString())
                englishVM.updateWord(updateWord)
                notify("Update successfully")
                callback.backToPrevious()
            }
        }

        binding.btnDelete.setOnClickListener {
            val topicIdForWord = w.topicId
            val word = w.word
            val mean = w.mean
            val imagePath = w.imagePath
            val wordId = w.wordId
            val w = Word(topicIdForWord, wordId, word, mean, imagePath)
            englishVM.deleteWord(w)
            callback.backToPrevious()
        }


    }

    override fun initViewBinding(container: ViewGroup?): FragmentSelectionWordBinding {
        return FragmentSelectionWordBinding.inflate(layoutInflater, container, false)
    }
}