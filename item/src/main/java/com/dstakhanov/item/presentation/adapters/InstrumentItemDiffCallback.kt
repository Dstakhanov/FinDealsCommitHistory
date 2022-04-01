package com.dstakhanov.item.presentation.adapters

import androidx.recyclerview.widget.DiffUtil

class InstrumentItemDiffCallback : DiffUtil.ItemCallback<com.dstakhanov.item.domain.InstrumentItem>() {
    override fun areItemsTheSame(oldItem: com.dstakhanov.item.domain.InstrumentItem, newItem: com.dstakhanov.item.domain.InstrumentItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: com.dstakhanov.item.domain.InstrumentItem, newItem: com.dstakhanov.item.domain.InstrumentItem): Boolean {
        return oldItem == newItem
    }
}