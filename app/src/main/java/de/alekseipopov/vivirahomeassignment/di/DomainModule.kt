package de.alekseipopov.vivirahomeassignment.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.alekseipopov.vivirahomeassignment.domian.SearchRepositoryUseCase
import de.alekseipopov.vivirahomeassignment.domian.SearchRepositoryUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun provideSearchRepositoryUseCase(
        useCase: SearchRepositoryUseCaseImpl
    ): SearchRepositoryUseCase

}