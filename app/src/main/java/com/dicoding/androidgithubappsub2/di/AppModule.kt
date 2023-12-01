package com.dicoding.androidgithubappsub2.di

import com.dicoding.androidgithubappsub2.data.repository.SettingRepository
import com.dicoding.androidgithubappsub2.data.repository.SettingRepositoryImpl
import com.dicoding.androidgithubappsub2.data.repository.UserRepository
import com.dicoding.androidgithubappsub2.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideUserRepo(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun provideSettingRepo(settingRepositoryImpl: SettingRepositoryImpl): SettingRepository
}