package com.dstakhanov.item.presentation.adapters

import android.view.View
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dstakhanov.findealscommithistory.R

class InstrumentItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val tvName = view.findViewById<TextView>(R.id.tv_name)
    val tvCount = view.findViewById<TextView>(R.id.tv_count)
    val tvPrice = view.findViewById<TextView>(R.id.tv_price)
    val tvDate = view.findViewById<TextView>(R.id.tv_date)
    val tvDirection  = view.findViewById<ImageView>(R.id.tv_direction)
}