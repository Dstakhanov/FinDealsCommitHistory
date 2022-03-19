package com.dstakhanov.findealscommithistory.data.info

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.dstakhanov.findealscommithistory.data.AppDatabase
import com.dstakhanov.findealscommithistory.data.info.network.ApiFactory
import com.dstakhanov.findealscommithistory.data.info.network.ApiService
import com.dstakhanov.findealscommithistory.domain.info.InstrumentInfo
import com.dstakhanov.findealscommithistory.domain.info.InstrumentInfoRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class InstrumentInfoRepositoryImpl @Inject constructor(
    private val mapper: InstrumentInfoMapper,
    private val instrumentInfoDao: InstrumentInfoDao,
    private val application: Application,
    private val apiService: ApiService
) : InstrumentInfoRepository {

    override fun getInstrumentInfoList(): LiveData<List<InstrumentInfo>> {
        return Transformations.map(instrumentInfoDao.getPriceList()) {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override fun getInstrumentInfo(fromSymbol: String): LiveData<InstrumentInfo> {
        return Transformations.map(instrumentInfoDao.getPriceInfoAboutInstrument(fromSymbol)) {
            mapper.mapDbModelToEntity(it)
        }
    }

    override suspend fun loadData() {
        while (true) {
            try {
                val topCoins = apiService.getTopInstrumentsInfo(limit = 50)
                val fSyms = mapper.mapNamesListToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fSyms = fSyms)
                val coinInfoDtoList = mapper.mapJsonContainerToListInstrumentInfo(jsonContainer)
                val dbModelList = coinInfoDtoList.map { mapper.mapDtoToDbModel(it) }
                instrumentInfoDao.insertPriceList(dbModelList)
            } catch (e: Exception) {
            }
            delay(10000)
        }
    }
}