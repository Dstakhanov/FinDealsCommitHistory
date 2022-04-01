package com.dstakhanov.core.item

import com.dstakhanov.utils.DateUtility
import com.dstakhanov.findealscommithistory.domain.item.InstrumentItem
import javax.inject.Inject

class InstrumentListMapper @Inject constructor(){

    @Inject
    lateinit var dateUtility: com.dstakhanov.utils.DateUtility

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