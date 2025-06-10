package com.example.contactmanagement.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.contactmanagement.commonUi.ContactInputs
import com.example.contactmanagement.model.ContactItem

@Composable
fun EditScreen(navController: NavController, viewModel: ContactViewModel, contactId: Int?) {
    var contact by remember { mutableStateOf<ContactItem?>(null) }

    LaunchedEffect(true) {
        val result = viewModel.getContactById(contactId)
        if (result == null) {
            navController.popBackStack()
        } else {
            contact = result
        }
    }

    Column {
        Text(text = "Edit Contact")
        ContactInputs(navController) { name, phoneNumber ->
            viewModel.editContact(
                contact?.id,
                name = name,
                phoneNumber = phoneNumber
            )
        }
    }
}