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
import com.example.eforkids.model.SubType

class SubTypeAdapter(private val context: Context, private var listener: OnItemClickListener) : RecyclerView.Adapter<SubTypeAdapter.SubTypeHolder>() {

    private var listSubType = emptyList<SubType>()
    companion object {
        const val KEY_SHOW_DETAIL: String = "KEY_SHOW_DETAIL"
    }
    inner class SubTypeHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var tvSubName: TextView = itemView.findViewById(R.id.tv_name_sub_type)
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            val storyId = position + 1
            val supTypeId = listSubType[position].typeId
            val subTypeId = listSubType[position].subTypeId
            val status = 1
            val subStoryName = listSubType[position].name
            val subStoryDetail = listSubType[position].detail

            listener.onItemClick(KEY_SHOW_DETAIL, SubType(storyId, supTypeId, subTypeId, subStoryName, subStoryDetail, status))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubTypeHolder {
        return SubTypeHolder(LayoutInflater.from(context).inflate(R.layout.item_sub_type, parent, false))
    }

    override fun onBindViewHolder(holder: SubTypeHolder, position: Int) {
        val subTypeStory = listSubType[position]
        holder.tvSubName.text = subTypeStory.name
    }

    override fun getItemCount(): Int {
        return listSubType.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(subTypeStory: List<SubType>){
        listSubType = subTypeStory
        notifyDataSetChanged()
    }
}