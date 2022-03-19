package com.dstakhanov.findealscommithistory.data.info

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.dstakhanov.findealscommithistory.domain.info.InstrumentInfo
import com.dstakhanov.findealscommithistory.domain.info.InstrumentInfoRepository
import com.dstakhanov.findealscommithistory.workers.RefreshDataWorker
import javax.inject.Inject

class InstrumentInfoRepositoryImpl @Inject constructor(
    private val mapper: InstrumentInfoMapper,
    private val instrumentInfoDao: InstrumentInfoDao,
    private val application: Application
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

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }
}