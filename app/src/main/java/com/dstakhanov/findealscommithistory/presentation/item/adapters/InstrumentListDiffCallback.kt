package com.dstakhanov.findealscommithistory.presentation.item.adapters

import androidx.recyclerview.widget.DiffUtil
import com.dstakhanov.findealscommithistory.domain.item.InstrumentItem

class InstrumentListDiffCallback (
    private val oldList: List<InstrumentItem>,
    private val newList: List<InstrumentItem>,
): DiffUtil.Callback() {
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