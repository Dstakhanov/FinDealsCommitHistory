package com.dstakhanov.workers.di

import com.dstakhanov.findealscommithistory.workers.ChildWorkerFactory
import com.dstakhanov.findealscommithistory.workers.RefreshDataWorker
import com.dstakhanov.workers.ChildWorkerFactory
import com.dstakhanov.workers.RefreshDataWorker
import com.dstakhanov.workers.di.WorkerKey
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