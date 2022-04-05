package com.dstakhanov.portfolio.di

import com.dstakhanov.portfolio.presentation.PortfolioFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelModule::class,
    ]
)
interface PortfolioComponent {

    fun inject(fragment: PortfolioFragment)

    @Component.Factory
    interface Factory {
        fun create(): PortfolioComponent
    }


}