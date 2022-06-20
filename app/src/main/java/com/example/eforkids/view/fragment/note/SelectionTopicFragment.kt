package com.example.eforkids.view.fragment.note

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.eforkids.databinding.FragmentSelectionTopicBinding
import com.example.eforkids.model.Topic
import com.example.eforkids.view.fragment.BaseFragment
import com.example.eforkids.viewmodel.EnglishVM

class SelectionTopicFragment : BaseFragment<FragmentSelectionTopicBinding>() {
    private lateinit var englishVM: EnglishVM
    private var uri: Uri? = null
    private var imagePath: String? = null
    override fun initViews() {

        englishVM = ViewModelProvider(this)[EnglishVM::class.java]
        setSelection()

        binding.ivSearch.setOnClickListener {
            if (binding.edtUpdateTopic.text.toString().isEmpty()) {
                notify("Input information")
                return@setOnClickListener

            } else {
                val url =
                    "https://www.google.com/search?hl=en&site=imghp&tbm=isch&source=hp&q=${binding.edtUpdateTopic.text}"
                val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                mContext.startActivity(i)
            }
        }
    }


    private fun setSelection() {
        val topic: Topic = mData as Topic
        imagePath = topic.imagePath
        binding.edtUpdateTopic.setText(topic.title)
        Glide.with(mContext).load(topic.imagePath).centerInside().into(binding.ivUpdateTopic)

        binding.ivBack.setOnClickListener {
            callback.backToPrevious()
        }

        binding.ivUpdateTopic.setOnClickListener {
            openGallery()
        }

        var topicId = topic.id
        var topicTitle: String
        var topicImage: String

        binding.btnUpdate.setOnClickListener {
            topicTitle = binding.edtUpdateTopic.text.toString()
            topicImage = imagePath.toString()
            if (binding.edtUpdateTopic.text.toString() == topic.title && imagePath == topic.imagePath) {
                notify("Change topic's information")
            } else {
                val updateTopic = Topic(topicId, topicTitle, topicImage)
                englishVM.updateTopic(updateTopic)
                notify("Update topic successfully")
                callback.backToPrevious()
            }
        }

        binding.btnDelete.setOnClickListener {
            topicId = topic.id
            topicTitle = binding.edtUpdateTopic.text.toString()
            topicImage = imagePath.toString()
            englishVM.deleteTopic(Topic(topicId, topicTitle, topicImage))
            englishVM.deleteWordById(topicId)
            callback.backToPrevious()
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
                Glide.with(mContext).load(imagePath).centerInside().into(binding.ivUpdateTopic)
            }
        }
    }


    override fun initViewBinding(container: ViewGroup?): FragmentSelectionTopicBinding {
        return FragmentSelectionTopicBinding.inflate(layoutInflater, container, false)
    }
}