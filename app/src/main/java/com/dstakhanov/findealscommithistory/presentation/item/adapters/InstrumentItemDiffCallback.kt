package com.dstakhanov.findealscommithistory.presentation.item.adapters

import androidx.recyclerview.widget.DiffUtil
import com.dstakhanov.findealscommithistory.domain.item.InstrumentItem

class InstrumentItemDiffCallback : DiffUtil.ItemCallback<InstrumentItem>() {
    override fun areItemsTheSame(oldItem: InstrumentItem, newItem: InstrumentItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: InstrumentItem, newItem: InstrumentItem): Boolean {
        return oldItem == newItem
    }
}