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
import com.example.eforkids.model.StoryType

class StoryTypeAdapter(private val context: Context, private val listener: OnItemClickListener)
    : RecyclerView.Adapter<StoryTypeAdapter.StoryTypeHolder>() {

    private var listImage = arrayListOf(
        R.drawable.short_stories,
        R.drawable.moral2,
        R.drawable.general_stories,
        R.drawable.animals,
        R.drawable.inspirational,
        R.drawable.motivational,
        R.drawable.classical,
        R.drawable.bedtimestories,
        R.drawable.aladin_stories,
        R.drawable.birbal_akbar,
        R.drawable.five_trietise,
        R.drawable.family_story,
        R.drawable.kids_stories,
        R.drawable.life_story,
        R.drawable.educationstory,
        R.drawable.fables,
        R.drawable.comicalstories,
        R.drawable.love,
        R.drawable.funystories,
        R.drawable.arabian,
        R.drawable.student,
        R.drawable.humorous,
        R.drawable.tenali,
        R.drawable.longsss,
        R.drawable.quotes,
        R.drawable.proverb,
        R.drawable.otherstories
    )
    private var listType = emptyList<StoryType>()
    companion object{
        const val KEY_OPEN_SUB: String = "KEY_OPEN_SUB"
    }
    inner class StoryTypeHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val ivStoryType: ImageView = itemView.findViewById(R.id.iv_story_type)
        val tvStoryTypeName: TextView = itemView.findViewById(R.id.tv_story_type_name)
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                listener.onItemClick(KEY_OPEN_SUB, listType[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryTypeHolder {
        return StoryTypeHolder(LayoutInflater.from(context).inflate(R.layout.item_story_name, parent, false))
    }

    override fun onBindViewHolder(holder: StoryTypeHolder, position: Int) {
        val currentType = listType[position]
        holder.tvStoryTypeName.text = currentType.nameType
        holder.ivStoryType.setImageResource(listImage[position])
    }

    override fun getItemCount(): Int {
        return listType.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(storyType: List<StoryType>){
        listType = storyType
        notifyDataSetChanged()
    }
}