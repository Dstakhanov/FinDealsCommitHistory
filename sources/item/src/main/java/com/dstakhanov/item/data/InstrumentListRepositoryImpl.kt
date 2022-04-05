package com.dstakhanov.item.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.dstakhanov.core.item.InstrumentListDao
import com.dstakhanov.item.domain.InstrumentItem
import com.dstakhanov.item.domain.InstrumentListRepository
import javax.inject.Inject

class InstrumentListRepositoryImpl @Inject constructor(
    private val instrumentListDao: InstrumentListDao,
    private val mapper: InstrumentListMapper
) : InstrumentListRepository {

    override suspend fun addInstrumentItem(instrumentItem: InstrumentItem) {
        instrumentListDao.addInstrumentItem(mapper.mapEntityToDbModel(instrumentItem))
    }

    override suspend fun deleteInstrumentItem(instrumentItem: InstrumentItem) {
        instrumentListDao.deleteInstrumentItem(instrumentItem.id)
    }

    override suspend fun editInstrumentItem(instrumentItem: InstrumentItem) {
        instrumentListDao.addInstrumentItem(mapper.mapEntityToDbModel(instrumentItem))
    }

    override fun getInstrumentList(): LiveData<List<InstrumentItem>> = Transformations.map(
        instrumentListDao.getInstrumentList()
    ) {
        mapper.mapListDbModelToListEntity(it)
    }

    override suspend fun getInstrumentItem(instrumentItemId: Int): InstrumentItem {
        val dbModel = instrumentListDao.getInstrumentItem(instrumentItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }
}