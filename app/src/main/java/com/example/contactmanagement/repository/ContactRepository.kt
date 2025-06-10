package com.example.contactmanagement.repository

import com.example.contactmanagement.model.ContactItem
import kotlinx.coroutines.flow.StateFlow

interface ContactRepository {
    suspend fun addContact(name: String, phoneNumber: String): Boolean

    fun getContacts() : StateFlow<List<ContactItem>>

    suspend fun deleteContact(id: Int): Boolean

    suspend fun editContact(id: Int, name: String, phoneNumber: String): Boolean
}