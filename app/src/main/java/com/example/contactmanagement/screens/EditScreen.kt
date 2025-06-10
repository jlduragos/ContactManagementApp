package com.example.contactmanagement.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Edit Contact", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(20.dp))
        ContactInputs(navController) { name, phoneNumber ->
            viewModel.editContact(
                contact?.id,
                name = name,
                phoneNumber = phoneNumber
            )
        }
    }
}