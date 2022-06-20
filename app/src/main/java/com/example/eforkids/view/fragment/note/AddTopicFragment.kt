package com.example.eforkids.view.fragment.note

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.eforkids.databinding.FragmentTopicDialogBinding
import com.example.eforkids.model.Topic
import com.example.eforkids.view.fragment.BaseFragment
import com.example.eforkids.view.fragment.note.MainNoteFragment
import com.example.eforkids.viewmodel.EnglishVM

class AddTopicFragment : BaseFragment<FragmentTopicDialogBinding>() {

    var uri: Uri? = null
    var imagePath: String? = null
    private lateinit var englishVM: EnglishVM
    private var topic: Topic? = null
    override fun initViews() {

        englishVM = ViewModelProvider(this)[EnglishVM::class.java]
        imagePath = uri.toString()
        binding.ivSearch.setOnClickListener {
            searchTopicInput()
        }
        binding.ivTopic.setOnClickListener {
            pickImageFromGallery()
        }

        binding.btnAdd.setOnClickListener {
            if (topic == null) {
                insertTopicToDatabase()
            }
        }

        binding.ivBack.setOnClickListener {
            callback.showFragment(AddTopicFragment::class.java, MainNoteFragment::class.java, null, true)
        }
    }

    private fun insertTopicToDatabase() {
        if(checkInputInformation()){
            val titleTopic = binding.edtTopic.text.toString()
            val titleImage = imagePath
            val topic = Topic(0, titleTopic, titleImage.toString())
            englishVM.addTopic(topic)
            notify("Add topic ${binding.edtTopic.text.toString().uppercase()} successfully")
            callback.showFragment(AddTopicFragment::class.java, MainNoteFragment::class.java, null, true)
        }
    }

    private fun checkInputInformation(): Boolean {
        if(binding.edtTopic.text.toString().isEmpty() || imagePath.isNullOrEmpty()){
            notify("Nhập thông tin")
            return false
        }
        return true
    }

    private fun pickImageFromGallery() {
        if (ActivityCompat.checkSelfPermission(mContext, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(READ_EXTERNAL_STORAGE),1)
        } else{
            val photoPicker = Intent(Intent.ACTION_PICK)
            photoPicker.type = "image/*"
            startActivityForResult(photoPicker, 101)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == 1){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, 101)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 101 && resultCode == Activity.RESULT_OK){
            if (data != null) {
                uri = data.data
                imagePath = uri.toString()
                Glide.with(mContext).load(imagePath).centerInside().into(binding.ivTopic)
            }
        }
    }

    private fun searchTopicInput() {
        if (binding.edtTopic.text.toString().isEmpty()) {
            notify("Nhập thông tin cho chủ đề")
            return
        }
        val url: String = "https://www.google.com/search?hl=en&site=imghp&tbm=isch&source=hp&q=${binding.edtTopic.text}"
        val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        mContext.startActivity(i)
    }

    override fun initViewBinding(container: ViewGroup?): FragmentTopicDialogBinding {
        return FragmentTopicDialogBinding.inflate(layoutInflater, container, false)
    }
}