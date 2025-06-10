package com.example.contactmanagement.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.contactmanagement.commonUi.ContactInputs

@Composable
fun AddContactScreen(
    navController: NavController,
    viewModel: ContactViewModel,
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Add Contact", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(20.dp))
        ContactInputs(navController) { name: String, phoneNumber ->
            viewModel.addContact(
                name = name,
                phoneNumber = phoneNumber
            )
        }
    }
}
