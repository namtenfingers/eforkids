package com.example.eforkids.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eforkids.OnItemClickListener
import com.example.eforkids.R
import com.example.eforkids.model.Content
import com.example.eforkids.model.SubContent
import com.squareup.picasso.Picasso

class SubContentAdapter(private val context: Context, private var listener: OnItemClickListener) :
    RecyclerView.Adapter<SubContentAdapter.SubContentHolder>() {

    companion object {
        const val KEY_OPEN_DETAIL_GAME = "KEY_OPEN_DETAIL_GAME"
    }

    private var listSubContent = emptyList<SubContent>()


    inner class SubContentHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val ivSubContent: ImageView = itemView.findViewById(R.id.iv_sub_content)
        var ivTicK: ImageView = itemView.findViewById(R.id.iv_tick)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val pos = adapterPosition
            val idSubContent = listSubContent[pos].id
            val idContent = listSubContent[pos].idContent
            val idSubOfSub = listSubContent[pos].idSubOfSub
            val tvContent = listSubContent[pos].name
            val ivContent = listSubContent[pos].imagePath
            val statusContent = listSubContent[pos].status
            val imgChecker = listSubContent[pos].imgChecker
            val spelling = listSubContent[pos].spelling
            val detail = listSubContent[pos].detail
            val tvMean = listSubContent[pos].mean
            val subContent = SubContent(
                idSubContent,
                idContent,
                idSubOfSub,
                tvContent,
                tvMean,
                ivContent,
                statusContent,
                imgChecker,
                spelling,
                detail
            )
            listener.onItemClick(KEY_OPEN_DETAIL_GAME, subContent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubContentHolder {
        return SubContentHolder(
            LayoutInflater.from(context).inflate(R.layout.item_sub_content, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SubContentHolder, position: Int) {
        val subContent = listSubContent[position]
        Glide.with(context).load(subContent.imagePath).into(holder.ivSubContent)
        Glide.with(context).load(subContent.imgChecker).into(holder.ivTicK)
    }

    override fun getItemCount(): Int {
        return listSubContent.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(subContent: List<SubContent>) {
        listSubContent = subContent
        notifyDataSetChanged()
    }
}