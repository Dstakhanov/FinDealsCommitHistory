package com.dstakhanov.item.presentation.adapters

import androidx.recyclerview.widget.DiffUtil

class InstrumentListDiffCallback(
    private val oldList: List<com.dstakhanov.item.domain.InstrumentItem>,
    private val newList: List<com.dstakhanov.item.domain.InstrumentItem>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }

}