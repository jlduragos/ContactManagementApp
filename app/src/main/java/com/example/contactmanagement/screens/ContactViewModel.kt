package com.example.contactmanagement.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactmanagement.model.ContactItem
import com.example.contactmanagement.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val contactRepository: ContactRepository
): ViewModel() {

    private val _contactList = contactRepository.getContacts()
    val contactList: StateFlow<List<ContactItem>> = _contactList

    fun addContact(name: String, phoneNumber: String) {
        viewModelScope.launch {
            contactRepository.addContact(name, phoneNumber)
        }
    }

    fun editContact(id: Int?, name: String, phoneNumber: String) {
        if (id == null) return
        viewModelScope.launch {
            contactRepository.editContact(id, name, phoneNumber)
        }
    }

    fun getContactById(contactId: Int?): ContactItem? {
        if (contactId == null) return null
        return contactList.value.firstOrNull { contact ->
            contact.id == contactId
        }
    }

    fun deleteContact(id: Int) {
        viewModelScope.launch {
            contactRepository.deleteContact(id)
        }
    }
}