package com.example.eforkids.adapter

import android.annotation.SuppressLint
import android.app.ActionBar
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eforkids.OnItemClickListener
import com.example.eforkids.R
import com.example.eforkids.model.Content
import com.squareup.picasso.Picasso

class ContentAdapter(
    private val context: Context,
    private var listener: OnItemClickListener
) : RecyclerView.Adapter<ContentAdapter.TypeQuizHolder>() {

    private var listContent = emptyList<Content>()

    companion object {
        const val KEY_OPEN_SUB_TYPE_QUIZ: String = "KEY_OPEN_SUB_TYPE_QUIZ"
    }

    inner class TypeQuizHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val ivContent: ImageView = itemView.findViewById(R.id.iv_content)
        val tvContent: TextView = itemView.findViewById(R.id.tv_content)
        val tvComplete: TextView = itemView.findViewById(R.id.tv_result)
        val tvPercentCompleted: TextView = itemView.findViewById(R.id.tv_percent_result)
        val vLineResult: TextView = itemView.findViewById(R.id.line_result)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val pos = adapterPosition
            val idContent = listContent[pos].id
            val tvContent = listContent[pos].name
            val ivContent = listContent[pos].imagePath
            val counterTure = listContent[pos].counterTrue
            listener.onItemClick(
                KEY_OPEN_SUB_TYPE_QUIZ,
                Content(idContent, tvContent, ivContent, counterTure)
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeQuizHolder {
        return TypeQuizHolder(
            LayoutInflater.from(context).inflate(R.layout.item_content, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TypeQuizHolder, position: Int) {
        val content = listContent[position]
        Picasso.with(context).load(content.imagePath).into(holder.ivContent)
        holder.tvContent.text = content.name
        holder.tvComplete.text = String.format("Vocabulary %d/20", content.counterTrue)
        holder.tvPercentCompleted.text = String.format("%d%%", content.counterTrue * 100 / 20)
        holder.vLineResult.width = (content.counterTrue * 307)/20
        holder.vLineResult.setBackgroundColor(holder.vLineResult.resources.getColor(R.color.navy))

    }

    override fun getItemCount(): Int {
        return listContent.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(content: List<Content>) {
        listContent = content
        notifyDataSetChanged()
    }
}