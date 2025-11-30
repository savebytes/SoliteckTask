package com.savebytes.solitecktask.di

import com.savebytes.solitecktask.domain.repo.HomeRepo
import com.savebytes.solitecktask.data.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeRepoModule {

    @Binds
    @Singleton
    abstract fun bindHomeRepo(
        impl: HomeRepository
    ): HomeRepo
}