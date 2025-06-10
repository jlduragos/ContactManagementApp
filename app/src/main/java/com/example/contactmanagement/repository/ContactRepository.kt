package com.example.contactmanagement.repository

import com.example.contactmanagement.model.ContactItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ContactRepository {
    fun addContact(name: String, phoneNumber: String): Boolean

    fun getContacts() : StateFlow<List<ContactItem>>

    fun deleteContact(id: Int): Boolean

    fun editContact(id: Int, name: String, phoneNumber: String): Boolean
}