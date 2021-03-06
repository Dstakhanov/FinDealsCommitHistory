package com.dstakhanov.findealscommithistory.data.info

import com.dstakhanov.findealscommithistory.util.DateUtility
import com.dstakhanov.findealscommithistory.data.info.network.model.InstrumentInfoDto
import com.dstakhanov.findealscommithistory.data.info.network.model.InstrumentInfoJsonContainerDto
import com.dstakhanov.findealscommithistory.data.info.network.model.InstrumentNamesListDto
import com.dstakhanov.findealscommithistory.domain.info.InstrumentInfo
import com.google.gson.Gson
import javax.inject.Inject

class InstrumentInfoMapper @Inject constructor(){
    @Inject
    lateinit var dateUtility: DateUtility

    fun mapDtoToDbModel(dto: InstrumentInfoDto) = InstrumentInfoDbModel(
        fromSymbol = dto.fromSymbol,
        toSymbol = dto.toSymbol,
        price = dto.price,
        lastUpdate = dto.lastUpdate,
        highDay = dto.highDay,
        lowDay = dto.lowDay,
        lastMarket = dto.lastMarket,
        imageUrl = BASE_IMAGE_URL + dto.imageUrl
    )

    fun mapJsonContainerToListInstrumentInfo(jsonContainer: InstrumentInfoJsonContainerDto): List<InstrumentInfoDto> {
        val result = mutableListOf<InstrumentInfoDto>()
        val jsonObject = jsonContainer.json ?: return result
        val instrumentKeySet = jsonObject.keySet()
        for (instrumentKey in instrumentKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(instrumentKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    InstrumentInfoDto::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    fun mapNamesListToString(namesListDto: InstrumentNamesListDto): String {
        return namesListDto.names?.map {
            it.instrumentName?.name
        }?.joinToString(",") ?: ""
    }

    fun mapDbModelToEntity(dbModel: InstrumentInfoDbModel) = InstrumentInfo(
        fromSymbol = dbModel.fromSymbol,
        toSymbol = dbModel.toSymbol,
        price = dbModel.price,
        lastUpdate = dateUtility.convertTimestampToTime(dbModel.lastUpdate),
        highDay = dbModel.highDay,
        lowDay = dbModel.lowDay,
        lastMarket = dbModel.lastMarket,
        imageUrl = dbModel.imageUrl
    )

    companion object {
        const val BASE_IMAGE_URL = "https://cryptocompare.com/"
    }
}