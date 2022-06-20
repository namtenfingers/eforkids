package com.example.eforkids.view.fragment.note

import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.eforkids.databinding.FragmentSpeakTopicBinding
import com.example.eforkids.model.Topic
import com.example.eforkids.model.Word
import com.example.eforkids.view.fragment.BaseFragment
import com.example.eforkids.view.fragment.note.MainNoteFragment
import java.util.*

class WordSpeakerFragment : BaseFragment<FragmentSpeakTopicBinding>(), TextToSpeech.OnInitListener {

    private lateinit var tts: TextToSpeech

    override fun initViews() {

        val word: Word = mData as Word
        tts = TextToSpeech(mContext, this)

        binding.ivBack.setOnClickListener {
            callback.showFragment(this::class.java, MainNoteFragment::class.java, null, true)
        }

        binding.tvNameTopic.text = word.word.uppercase()
        Glide.with(requireContext()).load(word.imagePath).centerCrop().into(binding.ivTopic)

        binding.ivMicro.setOnClickListener {
            speakTopic()
        }

    }

    private fun speakTopic() {
        val text = binding.tvNameTopic.text.toString()
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
    }

    override fun initViewBinding(container: ViewGroup?): FragmentSpeakTopicBinding {
        return FragmentSpeakTopicBinding.inflate(layoutInflater, container, false)
    }


    override fun onDestroy() {
        tts.stop()
        tts.shutdown()
        super.onDestroy()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","The Language specified is not supported!")
            } else {
                binding.ivMicro.isEnabled = true
            }

        } else {
            Log.e("TTS", "Initilization Failed!")
        }
    }
}