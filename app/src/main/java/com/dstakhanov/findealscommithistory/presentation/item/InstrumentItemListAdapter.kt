package com.dstakhanov.findealscommithistory.presentation.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dstakhanov.findealscommithistory.R
import com.dstakhanov.findealscommithistory.domain.item.InstrumentItem

class InstrumentItemListAdapter :
    ListAdapter<InstrumentItem, InstrumentItemViewHolder>(InstrumentItemDiffCallback()) {

    var onInstrumentItemLongClickListener: ((InstrumentItem) -> Unit)? = null
    var onInstrumentItemClickListener: ((InstrumentItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstrumentItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_DISABLED -> R.layout.item_instrument_disabled
            VIEW_TYPE_ENABLED -> R.layout.item_instrument_enabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return InstrumentItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: InstrumentItemViewHolder, position: Int) {
        val instrumentItem = getItem(position)
        with(viewHolder) {
            view.setOnLongClickListener {
                onInstrumentItemLongClickListener?.invoke(instrumentItem)
                true
            }
            view.setOnClickListener {
                onInstrumentItemClickListener?.invoke(instrumentItem)
            }
            tvName.text = instrumentItem.symbol
            tvCount.text = instrumentItem.count.toString()
            tvPrice.text = instrumentItem.price.toString()
            tvDate.text = instrumentItem.createDate.toString()
        }

    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }


    companion object {

        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101
        const val MAX_POOL_SIZE = 30
    }
}