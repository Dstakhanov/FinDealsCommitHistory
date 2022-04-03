package com.dstakhanov.info.presentation.adapters

import androidx.recyclerview.widget.DiffUtil

object InstrumentInfoDiffCallback : DiffUtil.ItemCallback<com.dstakhanov.info.domain.InstrumentInfo>() {
    override fun areItemsTheSame(oldItem: com.dstakhanov.info.domain.InstrumentInfo, newItem: com.dstakhanov.info.domain.InstrumentInfo): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: com.dstakhanov.info.domain.InstrumentInfo, newItem: com.dstakhanov.info.domain.InstrumentInfo): Boolean {
        return oldItem == newItem
    }
}