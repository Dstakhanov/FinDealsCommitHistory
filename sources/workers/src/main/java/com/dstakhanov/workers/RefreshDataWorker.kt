package com.dstakhanov.workers

import android.content.Context
import androidx.work.*
import com.dstakhanov.info.data.InstrumentInfoDao
import com.dstakhanov.info.data.InstrumentInfoMapper
import com.dstakhanov.api.ApiService
import kotlinx.coroutines.delay
import javax.inject.Inject

class RefreshDataWorker (
    context: Context,
    workerParameters: WorkerParameters,
    private val instrumentInfoDao: com.dstakhanov.info.data.InstrumentInfoDao,
    private val apiService: com.dstakhanov.api.ApiService,
    private val mapper: com.dstakhanov.info.data.InstrumentInfoMapper
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
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

    companion object {

        const val NAME = "RefreshDataWorker"

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
        }
    }

    class Factory @Inject constructor(
        private val coinInfoDao: com.dstakhanov.info.data.InstrumentInfoDao,
        private val apiService: com.dstakhanov.api.ApiService,
        private val mapper: com.dstakhanov.info.data.InstrumentInfoMapper
    ) : ChildWorkerFactory {
        override fun create(
            context: Context,
            workerParameters: WorkerParameters
        ): ListenableWorker {
            return RefreshDataWorker(
                context,
                workerParameters,
                coinInfoDao,
                apiService,
                mapper
            )
        }

    }
}