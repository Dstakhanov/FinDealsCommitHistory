package com.dstakhanov.findealscommithistory.data.info.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "price_list")
data class InstrumentInfoDto(
    @SerializedName("TYPE")
    @Expose
    val type: String?,
    @SerializedName("MARKET")
    @Expose
    val market: String?,
    @PrimaryKey
    @SerializedName("FROMSYMBOL")
    @Expose
    val fromSymbol: String,
    @SerializedName("TOSYMBOL")
    @Expose
    val toSymbol: String?,
    @SerializedName("FLAGS")
    @Expose
    val flags: String?,
    @SerializedName("PRICE")
    @Expose
    val price: String?,
    @SerializedName("LASTUPDATE")
    @Expose
    val lastUpdate: Long?,
    @SerializedName("MEDIAN")
    @Expose
    val median: Double?,
    @SerializedName("LASTVOLUME")
    @Expose
    val lastvolume: Double?,
    @SerializedName("LASTVOLUMETO")
    @Expose
    val lastvolumeto: Double?,
    @SerializedName("LASTTRADEID")
    @Expose
    val lasttradeid: String?,
    @SerializedName("VOLUMEDAY")
    @Expose
    val volumeday: Double?,
    @SerializedName("VOLUMEDAYTO")
    @Expose
    val volumedayto: Double?,
    @SerializedName("VOLUME24HOUR")
    @Expose
    val volume24hour: Double?,
    @SerializedName("VOLUME24HOURTO")
    @Expose
    val volume24hourto: Double?,
    @SerializedName("OPENDAY")
    @Expose
    val openday: Double?,
    @SerializedName("HIGHDAY")
    @Expose
    val highDay: String?,
    @SerializedName("LOWDAY")
    @Expose
    val lowDay: String?,
    @SerializedName("OPEN24HOUR")
    @Expose
    val open24hour: Double?,
    @SerializedName("HIGH24HOUR")
    @Expose
    val high24hour: Double?,
    @SerializedName("LOW24HOUR")
    @Expose
    val low24hour: Double?,
    @SerializedName("LASTMARKET")
    @Expose
    val lastMarket: String?,
    @SerializedName("VOLUMEHOUR")
    @Expose
    val volumehour: Double?,
    @SerializedName("VOLUMEHOURTO")
    @Expose
    val volumehourto: Double?,
    @SerializedName("OPENHOUR")
    @Expose
    val openhour: Double?,
    @SerializedName("HIGHHOUR")
    @Expose
    val highhour: Double?,
    @SerializedName("LOWHOUR")
    @Expose
    val lowhour: Double?,
    @SerializedName("TOPTIERVOLUME24HOUR")
    @Expose
    val toptiervolume24hour: Double?,
    @SerializedName("CHANGE24HOUR")
    @Expose
    val change24hour: Double?,
    @SerializedName("CHANGEPCT24HOUR")
    @Expose
    val changepct24hour: Double?,
    @SerializedName("CHANGEDAY")
    @Expose
    val changeday: Double?,
    @SerializedName("CHANGEPCTDAY")
    @Expose
    val changepctday: Double?,
    @SerializedName("CHANGEHOUR")
    @Expose
    val changehour: Double?,
    @SerializedName("CHANGEPCTHOUR")
    @Expose
    val changepcthour: Double?,
    @SerializedName("CONVERSIONTYPE")
    @Expose
    val conversiontype: String?,
    @SerializedName("CONVERSIONSYMBOL")
    @Expose
    val conversionsymbol: String?,
    @SerializedName("SUPPLY")
    @Expose
    val supply: Integer?,
    @SerializedName("MKTCAP")
    @Expose
    val mktcap: Double?,
    @SerializedName("MKTCAPPENALTY")
    @Expose
    val mktcappenalty: Integer?,
    @SerializedName("CIRCULATINGSUPPLY")
    @Expose
    val circulatingsupply: Integer?,
    @SerializedName("CIRCULATINGSUPPLYMKTCAP")
    @Expose
    val circulatingsupplymktcap: Double?,
    @SerializedName("TOTALVOLUME24H")
    @Expose
    val totalvolume24h: Double?,
    @SerializedName("TOTALVOLUME24HTO")
    @Expose
    val totalvolume24hto: Double?,
    @SerializedName("TOTALTOPTIERVOLUME24H")
    @Expose
    val totaltoptiervolume24h: Double?,
    @SerializedName("TOTALTOPTIERVOLUME24HTO")
    @Expose
    val totaltoptiervolume24hto: Double?,
    @SerializedName("IMAGEURL")
    @Expose
    val imageUrl: String?
)