package com.example.eforkids.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eforkids.OnActionCallback
import com.example.eforkids.OnItemClickListener
import com.example.eforkids.R
import com.example.eforkids.model.StoryType
import com.example.eforkids.model.Topic
import com.example.eforkids.view.fragment.note.MainNoteFragment
import com.example.eforkids.view.fragment.note.SelectionTopicFragment

class TopicAdapter(
    private val context: Context,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<TopicAdapter.TopicHolder>() {

    private var listTopic = emptyList<Topic>()

    companion object {
        const val KEY_CHANGE_TOPIC: String = "KEY_CHANGE_TOPIC"
        const val KEY_OPEN_NOTE_WORD: String = "KEY_OPEN_NOTE_WORD"
    }

    inner class TopicHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener {
        var tvTopicTitle: TextView = itemView.findViewById(R.id.tv_topic)
        var ivTopicImage: ImageView = itemView.findViewById(R.id.iv_topic)

        init {
            itemView.setOnLongClickListener(this)
            itemView.setOnClickListener(this)
        }


        override fun onLongClick(p0: View?): Boolean {
            val position = adapterPosition
            val idTopic = listTopic[position].id
            val tvTopicTitle = listTopic[position].title
            val ivTopicImage = listTopic[position].imagePath
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(KEY_CHANGE_TOPIC, Topic(idTopic, tvTopicTitle, ivTopicImage))
            }
            return true
        }

        override fun onClick(p0: View?){
            val position = adapterPosition
            val idTopic = listTopic[position].id
            val tvTopicTitle = listTopic[position].title
            val ivTopicImage = listTopic[position].imagePath
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(KEY_OPEN_NOTE_WORD, Topic(idTopic, tvTopicTitle, ivTopicImage))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_topic, parent, false)
        return TopicHolder(itemView)
    }

    override fun onBindViewHolder(holder: TopicHolder, position: Int) {
        val topic = listTopic[position]
        holder.tvTopicTitle.text = topic.title
        Glide.with(context).load(topic.imagePath).centerCrop().into(holder.ivTopicImage)
    }

    override fun getItemCount(): Int {
        return listTopic.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(topic: List<Topic>) {
        listTopic = topic
        notifyDataSetChanged()
    }
}