package com.example.eforkids.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eforkids.OnItemClickListener
import com.example.eforkids.R
import com.example.eforkids.model.SubContent
import org.w3c.dom.Text
import java.util.*

class SubContentLearnAdapter(private var context: Context, private var listener: OnItemClickListener) :
    RecyclerView.Adapter<SubContentLearnAdapter.SubContentLearnHolder>() {
    private lateinit var tts: TextToSpeech
    private var listSubContent = emptyList<SubContent>()
    companion object{
        const val KEY_NOTE_WORD: String = "KEY_NOTE_WORD"
    }

    inner class SubContentLearnHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener, TextToSpeech.OnInitListener {
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvMean: TextView = itemView.findViewById(R.id.tv_mean)
        val tvSpelling: TextView = itemView.findViewById(R.id.tv_spelling)
        val tvDetail: TextView = itemView.findViewById(R.id.tv_detail)
        val ivSubContent: ImageView = itemView.findViewById(R.id.iv_sub_content)
        private val ivVolume: ImageView = itemView.findViewById(R.id.iv_volume)
        private val btnNote: Button = itemView.findViewById(R.id.btn_note)
        init {
            btnNote.setOnClickListener(this)
            ivVolume.setOnClickListener{
                speak()
            }
        }


        private fun speak() {
            tts = TextToSpeech(context, this)
            tts!!.speak(tvName.toString(), TextToSpeech.QUEUE_FLUSH, null,"")
        }

        override fun onClick(p0: View?) {
            listener.onItemClick(KEY_NOTE_WORD, null)
        }

        override fun onInit(status: Int) {
            if (status == TextToSpeech.SUCCESS) {
                // set US English as language for tts
                val result = tts!!.setLanguage(Locale.UK)

                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS","The Language specified is not supported!")
                } else {
                    ivVolume.isEnabled = true
                }

            } else {
                Log.e("TTS", "Initilization Failed!")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubContentLearnHolder {
        return SubContentLearnHolder(
            LayoutInflater.from(context).inflate(R.layout.item_sub_content_learn, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SubContentLearnHolder, position: Int) {
        val subContent = listSubContent[position]
        holder.tvName.text = subContent.name
        holder.tvMean.text = subContent.mean
        holder.tvSpelling.text = subContent.spelling
        holder.tvDetail.text = subContent.detail
        Glide.with(context).load(subContent.imagePath).into(holder.ivSubContent)
    }

    override fun getItemCount(): Int {
        return listSubContent.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(subContent: List<SubContent>){
        listSubContent = subContent
        notifyDataSetChanged()
    }

    fun onDestroy(){
        tts.stop()
        tts.shutdown()
    }
}