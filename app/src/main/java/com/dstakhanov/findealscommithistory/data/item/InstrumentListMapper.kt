package com.dstakhanov.findealscommithistory.data.item

import com.dstakhanov.findealscommithistory.domain.item.InstrumentItem
import javax.inject.Inject

class InstrumentListMapper @Inject constructor(){

    fun mapEntityToDbModel(instrumentItem: InstrumentItem) = InstrumentItemDbModel(
        id = instrumentItem.id,
        symbol = instrumentItem.symbol,
        count = instrumentItem.count,
        price = instrumentItem.price,
        createDate = instrumentItem.createDate,
        enabled = instrumentItem.enabled
    )

    fun mapDbModelToEntity(instrumentItemDbModel: InstrumentItemDbModel) = InstrumentItem(
        id = instrumentItemDbModel.id,
        symbol = instrumentItemDbModel.symbol,
        count = instrumentItemDbModel.count,
        price = instrumentItemDbModel.price,
        createDate = instrumentItemDbModel.createDate,
        enabled = instrumentItemDbModel.enabled
    )

    fun mapListDbModelToListEntity(list: List<InstrumentItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}