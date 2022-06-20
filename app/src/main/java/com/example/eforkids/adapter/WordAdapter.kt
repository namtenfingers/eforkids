package com.example.eforkids.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eforkids.OnItemClickListener
import com.example.eforkids.R
import com.example.eforkids.model.Word

class WordAdapter(private val context: Context, private var listener: OnItemClickListener) :
    RecyclerView.Adapter<WordAdapter.WordHolder>() {
    companion object{
        const val KEY_CHANGE_WORD: String = "KEY_CHANGE_WORD"
        const val KEY_SPEAK_WORD: String = "KEY_SPEAK_WORD"
    }
    private var listWord = emptyList<Word>()
    inner class WordHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener, View.OnLongClickListener {
        val tvWord: TextView = itemView.findViewById(R.id.tv_word)
        val tvMean: TextView = itemView.findViewById(R.id.tv_mean)
        val ivWord: ImageView = itemView.findViewById(R.id.iv_word)
        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        override fun onClick(p0: View?) {
            val pos = adapterPosition
            val word = listWord[pos].word
            val mean = listWord[pos].mean
            val imagePath = listWord[pos].imagePath
            val topicIdForWord = listWord[pos].topicId
            val wordId = listWord[pos].wordId
            val data = Word(topicIdForWord, wordId, word, mean, imagePath)
            if(pos != RecyclerView.NO_POSITION){
                listener.onItemClick(KEY_SPEAK_WORD, data)
            }
        }

        override fun onLongClick(p0: View?): Boolean {
            val pos = adapterPosition
            val word = listWord[pos].word
            val mean = listWord[pos].mean
            val imagePath = listWord[pos].imagePath
            val topicIdForWord = listWord[pos].topicId
            val wordId = listWord[pos].wordId
            val data = Word(topicIdForWord, wordId, word, mean, imagePath)
            if(pos != RecyclerView.NO_POSITION){
                listener.onItemClick(KEY_CHANGE_WORD, data)
            }
            return true
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordHolder {
        return WordHolder(LayoutInflater.from(context).inflate(R.layout.item_word, parent, false))
    }

    override fun onBindViewHolder(holder: WordHolder, position: Int) {
        val word = listWord[position]
        holder.tvWord.text = word.word
        holder.tvMean.text = String.format("(%s)", word.mean)
        Glide.with(context).load(word.imagePath).centerCrop().into(holder.ivWord)
    }

    override fun getItemCount(): Int {
        return listWord.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(word: List<Word>){
        listWord = word
        notifyDataSetChanged()
    }
}