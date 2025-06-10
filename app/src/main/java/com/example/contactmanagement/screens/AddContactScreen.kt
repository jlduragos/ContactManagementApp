package com.example.contactmanagement.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.contactmanagement.commonUi.ContactInputs

@Composable
fun AddContactScreen(
    navController: NavController,
    viewModel: ContactViewModel,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Text("Add Contact", style = MaterialTheme.typography.headlineLarge)
        ContactInputs(navController) { name: String, phoneNumber ->
            viewModel.addContact(
                name = name,
                phoneNumber = phoneNumber
            )
        }
    }
}
