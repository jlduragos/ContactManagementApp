package com.example.contactmanagement.di

import com.example.contactmanagement.repository.ContactRepository
import com.example.contactmanagement.repository.ContactRepositoryImpl
import com.example.contactmanagement.repository.model.ContactDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providesContactRepository(
        contactDao: ContactDao
    ): ContactRepository {
        return ContactRepositoryImpl(contactDao)
    }
}