package com.example.eforkids.view.fragment.note

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.eforkids.databinding.FragmentAddWordBinding
import com.example.eforkids.model.Topic
import com.example.eforkids.model.Word
import com.example.eforkids.view.fragment.BaseFragment
import com.example.eforkids.viewmodel.EnglishVM

class AddWordFragment : BaseFragment<FragmentAddWordBinding>() {

    private var uri: Uri? = null
    private var imagePath: String? = null
    private lateinit var englishVM: EnglishVM
    private var word: Word? = null
    private lateinit var topic: Topic
    override fun initViews() {
        englishVM = ViewModelProvider(this)[EnglishVM::class.java]
        imagePath = uri.toString()
        binding.ivBack.setOnClickListener {
            callback.backToPrevious()
        }

        binding.ivSearch.setOnClickListener {
            searchInput()
        }

        binding.ivWord.setOnClickListener {
            pickImageFromGallery()
        }

        binding.btnAdd.setOnClickListener {
            if(word == null){
                insertWordDatabase()
            }
        }

    }

    private fun insertWordDatabase() {
        topic = mData as Topic
        if(binding.edtWord.text.toString().isEmpty() || binding.edtMean.text.toString().isEmpty() || imagePath.isNullOrEmpty()){
            notify("Nhập thông tin")
            return
        }
        val wordId = topic.id
        val word = binding.edtWord.text.toString()
        val mean = binding.edtMean.text.toString()
        val wordImage = imagePath
        val addedWord = Word(wordId, 0,word, mean, wordImage.toString())
        englishVM.addWord(addedWord)
        notify("Added ${word.uppercase()} successfully")
        callback.backToPrevious()
    }

    private fun pickImageFromGallery() {
        val photoPicker = Intent(Intent.ACTION_PICK)
        photoPicker.type = "image/*"
        startActivityForResult(photoPicker, 101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 101 && resultCode == Activity.RESULT_OK){
            if (data != null) {
                uri = data.data
                imagePath = uri.toString()
                Glide.with(mContext).load(imagePath).centerInside().into(binding.ivWord)
            }
        }
    }

    private fun searchInput() {
        if (binding.edtWord.text.toString().isEmpty()) {
            notify("Nhập thông tin cho chủ đề")
            return
        }
        val url: String = "https://www.google.com/search?hl=en&site=imghp&tbm=isch&source=hp&q=${binding.edtWord.text}"
        val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        mContext.startActivity(i)
    }

    override fun initViewBinding(container: ViewGroup?): FragmentAddWordBinding {
        return FragmentAddWordBinding.inflate(layoutInflater, container, false)
    }
}