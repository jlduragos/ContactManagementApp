package com.example.contactmanagement.di

import android.app.Application
import android.content.Context
import com.example.contactmanagement.AppDatabase
import com.example.contactmanagement.repository.model.ContactDao
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @Provides
    fun providesContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
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