package com.example.contactmanagement.repository

import com.example.contactmanagement.model.ContactItem
import com.example.contactmanagement.repository.model.Contact
import com.example.contactmanagement.repository.model.ContactDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class ContactRepositoryImpl @Inject constructor(
    private val contactDao: ContactDao
): ContactRepository {
    override fun addContact(name: String, phoneNumber: String): Boolean {
        TODO("Not yet implemented")
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

    override fun deleteContact(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun editContact(id: Int, name: String, phoneNumber: String): Boolean {
        TODO("Not yet implemented")
    }
}