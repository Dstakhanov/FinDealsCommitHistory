package com.dstakhanov.info.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dstakhanov.info.R
import com.dstakhanov.info.databinding.ItemInstrumentInfoBinding
import com.dstakhanov.info.domain.InstrumentInfo
import com.squareup.picasso.Picasso


class InstrumentInfoAdapter(
    private val context: Context
) : ListAdapter<InstrumentInfo, InstrumentInfoViewHolder>(
    InstrumentInfoDiffCallback
) {

    var onInstrumentClickListener: OnInstrumentClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstrumentInfoViewHolder {
        val binding = ItemInstrumentInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return InstrumentInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InstrumentInfoViewHolder, position: Int) {
        val instrument = getItem(position)
        with(holder.binding) {
            with(instrument) {
                val symbolsTemplate = context.resources.getString(R.string.symbols_template)
                val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
                tvSymbols.text = String.format(symbolsTemplate, fromSymbol, toSymbol)
                tvPrice.text = price
                tvLastUpdate.text =
                    String.format(lastUpdateTemplate, lastUpdate)
                Picasso.get().load(imageUrl).into(ivLogoInstrument)
                root.setOnClickListener {
                    onInstrumentClickListener?.onInstrumentClick(this)
                }
            }
        }

    }


    interface OnInstrumentClickListener {
        fun onInstrumentClick(instrumentPriceInfo: InstrumentInfo)
    }
}