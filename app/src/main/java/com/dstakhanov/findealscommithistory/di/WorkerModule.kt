package com.dstakhanov.findealscommithistory.di

import com.dstakhanov.findealscommithistory.workers.ChildWorkerFactory
import com.dstakhanov.findealscommithistory.workers.RefreshDataWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    fun bindRefreshDataWorkerFactory(worker: RefreshDataWorker.Factory): ChildWorkerFactory
}