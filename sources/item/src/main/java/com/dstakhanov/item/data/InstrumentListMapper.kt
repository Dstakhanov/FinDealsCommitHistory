package com.dstakhanov.item.data

import com.dstakhanov.core.item.InstrumentItemDbModel
import com.dstakhanov.item.domain.InstrumentItem
import com.dstakhanov.utils.DateUtility
import javax.inject.Inject

class InstrumentListMapper @Inject constructor(){


    private lateinit var dateUtility: DateUtility

    fun mapEntityToDbModel(instrumentItem: InstrumentItem) = InstrumentItemDbModel(
        id = instrumentItem.id,
        symbol = instrumentItem.symbol,
        count = instrumentItem.count,
        price = instrumentItem.price,
        createDate = dateUtility.convertDateToLong(instrumentItem.createDate),
        enabled = instrumentItem.enabled,
        direction = instrumentItem.direction
    )

    fun mapDbModelToEntity(instrumentItemDbModel: InstrumentItemDbModel) = InstrumentItem(
        id = instrumentItemDbModel.id,
        symbol = instrumentItemDbModel.symbol,
        count = instrumentItemDbModel.count,
        price = instrumentItemDbModel.price,
        createDate = dateUtility.convertTimestampToDateTime(instrumentItemDbModel.createDate),
        enabled = instrumentItemDbModel.enabled,
        direction = instrumentItemDbModel.direction
    )


    fun mapListDbModelToListEntity(list: List<InstrumentItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}