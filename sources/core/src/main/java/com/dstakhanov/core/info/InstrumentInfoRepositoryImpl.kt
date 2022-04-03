package com.dstakhanov.core.info

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.dstakhanov.findealscommithistory.workers.RefreshDataWorker
import javax.inject.Inject

class InstrumentInfoRepositoryImpl @Inject constructor(
    private val mapper: InstrumentInfoMapper,
    private val instrumentInfoDao: InstrumentInfoDao,
    private val application: Application
) : com.dstakhanov.info.domain.InstrumentInfoRepository {

    override fun getInstrumentInfoList(): LiveData<List<com.dstakhanov.info.domain.InstrumentInfo>> {
        return Transformations.map(instrumentInfoDao.getPriceList()) {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override fun getInstrumentInfo(fromSymbol: String): LiveData<com.dstakhanov.info.domain.InstrumentInfo> {
        return Transformations.map(instrumentInfoDao.getPriceInfoAboutInstrument(fromSymbol)) {
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }
}