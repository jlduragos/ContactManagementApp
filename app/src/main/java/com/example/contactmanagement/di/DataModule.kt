package com.example.contactmanagement.di

import android.app.Application
import android.content.Context
import com.example.contactmanagement.AppDatabase
import com.example.contactmanagement.repository.model.ContactDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun providesContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun providesDatabase(
        context: Context
    ): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun providesContactDao(database: AppDatabase): ContactDao {
        return database.contactDao()
    }
}