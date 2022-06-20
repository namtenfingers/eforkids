package com.example.eforkids.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eforkids.OnItemClickListener
import com.example.eforkids.R
import com.example.eforkids.model.Content

class ContentLearnAdapter(private var context: Context, private var listener: OnItemClickListener) :
    RecyclerView.Adapter<ContentLearnAdapter.ContentLearnHolder>() {
    companion object{
        const val KEY_SHOW_DETAIL: String = "KEY_SHOW_DETAIL"
    }
    private var listContent = emptyList<Content>()

    inner class ContentLearnHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val tvContentName: TextView = itemView.findViewById(R.id.tv_topic_learn)
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val pos = adapterPosition
            val idContent = listContent[pos].id
            listener.onItemClick(KEY_SHOW_DETAIL, idContent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentLearnHolder {
        return ContentLearnHolder(
            LayoutInflater.from(context).inflate(R.layout.item_topic_for_learn, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ContentLearnHolder, position: Int) {
        val content = listContent[position]
        holder.tvContentName.text = content.name
    }

    override fun getItemCount(): Int {
        return listContent.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(content: List<Content>){
        listContent = content
        notifyDataSetChanged()
    }
}