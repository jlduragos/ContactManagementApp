package com.example.contactmanagement.repository

import com.example.contactmanagement.model.ContactItem
import com.example.contactmanagement.repository.model.Contact
import com.example.contactmanagement.repository.model.ContactDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ContactRepositoryImpl @Inject constructor(
    private val contactDao: ContactDao
): ContactRepository {
    override suspend fun addContact(name: String, phoneNumber: String): Boolean = withContext(Dispatchers.IO) {
        contactDao.insertContact(
            Contact(
                name = name,
                phoneNumber = phoneNumber
            )
        )
        return@withContext true
    }

    override fun getContacts(): StateFlow<List<ContactItem>> {
        return contactDao.getAllContacts()
            .map { contacts ->
                contacts.map { contact ->
                    ContactItem().apply {
                        this.id = contact.id
                        this.name = contact.name
                        this.phoneNumber = contact.phoneNumber
                    }
                }
            }
            .stateIn(
                scope = CoroutineScope(Dispatchers.IO),
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
    }

    override suspend fun deleteContact(id: Int): Boolean {
        return withContext(Dispatchers.IO) {
            contactDao.deleteContactById(id)
            return@withContext true
        }
    }

    override suspend fun editContact(id: Int, name: String, phoneNumber: String): Boolean {
        return withContext(Dispatchers.IO) {
            contactDao.insertContact(
                Contact(
                    id = id,
                    name = name,
                    phoneNumber = phoneNumber
                )
            )
            return@withContext true
        }
    }
}