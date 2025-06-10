package com.example.contactmanagement.di

import com.example.contactmanagement.repository.ContactRepository
import com.example.contactmanagement.repository.ContactRepositoryImpl
import com.example.contactmanagement.repository.model.ContactDao
import dagger.Provides

class RepositoryModule {
    @Provides
    fun providesContactRepository(
        contactDao: ContactDao
    ): ContactRepository {
        return ContactRepositoryImpl(contactDao)
    }
}