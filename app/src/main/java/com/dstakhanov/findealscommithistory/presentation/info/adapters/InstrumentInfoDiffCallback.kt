package com.dstakhanov.findealscommithistory.presentation.info.adapters

import androidx.recyclerview.widget.DiffUtil
import com.dstakhanov.findealscommithistory.domain.info.InstrumentInfo

object InstrumentInfoDiffCallback : DiffUtil.ItemCallback<InstrumentInfo>() {
    override fun areItemsTheSame(oldItem: InstrumentInfo, newItem: InstrumentInfo): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: InstrumentInfo, newItem: InstrumentInfo): Boolean {
        return oldItem == newItem
    }
}