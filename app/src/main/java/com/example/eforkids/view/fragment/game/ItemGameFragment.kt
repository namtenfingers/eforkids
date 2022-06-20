package com.example.eforkids.view.fragment.game

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.eforkids.adapter.GridDataAdapter
import com.example.eforkids.databinding.FragmentDetailGameBinding
import com.example.eforkids.model.Content
import com.example.eforkids.model.SubContent
import com.example.eforkids.view.fragment.BaseFragment
import com.example.eforkids.viewmodel.EnglishVM
import java.util.*
import kotlin.collections.ArrayList

class ItemGameFragment : BaseFragment<FragmentDetailGameBinding>(), TextToSpeech.OnInitListener {
    companion object {
        const val URL_ICON_TICK: String =
            "https://img.freepik.com/free-vector/correct-sign-right-mark-icon-set-green-tick-flat-symbol-check-ok-yes-marks-vote-decision_473851-126.jpg"
    }

    private var status: Int? = null
    private var imgCheck: String? = null

    private lateinit var englishVM: EnglishVM
    private lateinit var subContent: SubContent
    private lateinit var arrCell: ArrayList<String>
    private lateinit var arrShuffle: ArrayList<String>
    private lateinit var gridData: GridView
    private lateinit var content: Content
    private lateinit var gridAnswer: GridView
    private lateinit var receivedData: ArrayList<*>
    private lateinit var tts: TextToSpeech
    private var index = 0
    override fun initViews() {


        status = 0
        imgCheck = ""
        englishVM = ViewModelProvider(this)[EnglishVM::class.java]
        receivedData = mData as ArrayList<*>
        subContent = receivedData[0] as SubContent
        content = receivedData[1] as Content
        tts = TextToSpeech(mContext, this)

        binding.tvSpelling.text = subContent.spelling
        binding.tvIndex.text = String.format("%d/20", subContent.idSubOfSub)
        Glide.with(mContext).load(subContent.imagePath).into(binding.ivSubContent)

        arrCell = arrayListOf()
        arrShuffle = arrayListOf()

        gridAnswer = binding.gridAnswer
        gridData = binding.gridData

        arrShuffle = setShuffleDataForCell()
        arrCell = setDataListCell()

        showAnswer()
        showData()

        setDataListCell()
        setShuffleDataForCell()

        pushData()

        binding.icBack.setOnClickListener {
            callback.backToPrevious()
        }

        binding.ivVolume.setOnClickListener {
            speakText()
        }
    }

    private fun speakText() {
        val text = subContent.name
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }


    private fun showData() {
        gridData.adapter = GridDataAdapter(mContext, arrShuffle)
    }

    private fun showAnswer() {
        gridAnswer.adapter = GridDataAdapter(mContext, arrCell)
    }

    private fun setDataListCell(): ArrayList<String> {
        val cellName = subContent.name
        val arrCell: ArrayList<String> = arrayListOf()
        for (i in cellName.indices step 1) {
            arrCell.add("")
        }
        return arrCell
    }

    private fun setShuffleDataForCell(): ArrayList<String> {
        val cellName = subContent.name
        val listCell: ArrayList<String> = arrayListOf()
        for (i in cellName.indices step 1) {
            listCell.add(cellName[i].toString())
        }
        listCell.shuffle()
        val arr = arrayListOf<String>()
        for (i in listCell.indices step 1) {
            arr.add(listCell[i])
        }
        return arr
    }

    private fun checkWin() {
        var s = ""
        for (i in arrCell) {
            s += i
        }
        if (s.uppercase() == subContent.name.uppercase()) {
            callback.showFragment(
                this::class.java,
                ShowStatusContent::class.java,
                subContent,
                true
            )
            if (subContent.status == 0) {
                updateSubContent()
                updateContent()
            } else {
                // do nothing
            }

        } else {
            notify("False Answer")
        }
    }


    private fun updateContent() {
        content.counterTrue = content.counterTrue + 1
        englishVM.updateContent(
            Content(
                content.id,
                content.name,
                content.imagePath,
                content.counterTrue
            )
        )
    }

    private fun updateSubContent() {
        status = 1
        imgCheck = if (status == 1) {
            URL_ICON_TICK
        } else {
            ""
        }
        englishVM.updateSubContent(
            SubContent(
                subContent.id,
                subContent.idContent,
                subContent.idSubOfSub,
                subContent.name,
                subContent.mean,
                subContent.imagePath,
                status!!,
                imgCheck!!,
                subContent.spelling,
                subContent.detail
            )
        )
    }

    private fun pushData() {
        gridData.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, pos, _ ->
                val s: String = parent.getItemAtPosition(pos) as String
                if (s.isNotEmpty() && index < arrCell.size) {
                    for (i in 0 until arrCell.size step 1) {
                        if (arrCell[i].isEmpty()) {
                            index = i
                            break
                        }
                    }
                    arrShuffle[pos] = ""
                    arrCell[index] = s
                    index++
                    showAnswer()
                    showData()

                    if (index == arrCell.size) {
                        checkWin()
                    }
                }
            }
        gridAnswer.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, pos, _ ->
                val s: String = parent.getItemAtPosition(pos) as String
                if (s.isNotEmpty()) {
                    index = pos
                    arrCell[pos] = ""
                    for (i in 0 until arrCell.size step 1) {
                        if (arrShuffle[i].isEmpty()) {
                            arrShuffle[i] = s
                            break
                        }
                    }
                    showAnswer()
                    showData()
                }
            }

    }

    override fun initViewBinding(container: ViewGroup?): FragmentDetailGameBinding {
        return FragmentDetailGameBinding.inflate(layoutInflater, container, false)
    }

    override fun onInit(p0: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.UK)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language specified is not supported!")
            } else {
                binding.ivVolume.isEnabled = true
            }

        } else {
            Log.e("TTS", "Failed!")
        }
    }

    override fun onDestroy() {
        tts.stop()
        tts.shutdown()
        super.onDestroy()
    }

}