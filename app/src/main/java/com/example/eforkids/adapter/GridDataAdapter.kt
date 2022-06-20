package com.example.eforkids.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.opengl.Visibility
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.eforkids.R

class GridDataAdapter(private val context: Context, private var arrCell: ArrayList<String>) : BaseAdapter(){
    override fun getCount(): Int {
        return arrCell.size
    }

    override fun getItem(p0: Int): Any {
        return arrCell[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(pos: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = View.inflate(context, R.layout.item_grid, null)
        val tvChar: TextView = view.findViewById(R.id.tv_character)

        val cell: String = arrCell[pos]
        tvChar.text = cell.uppercase()
        return view
    }
}