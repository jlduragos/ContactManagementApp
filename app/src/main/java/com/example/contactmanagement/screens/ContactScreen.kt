package com.example.contactmanagement.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.contactmanagement.commonUi.ContactItem

@Composable
fun ContactScreen(viewModel: ContactViewModel, navController: NavController, modifier: Modifier) {
    val contacts by viewModel.contactList.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    LazyColumn(modifier = modifier) {
        items(contacts.size) { index ->
            val contact = contacts[index]
            ContactItem(
                contact = contact,
                onEdit = { id ->
                    navController.navigate("editContact/$id")
                },
                onDelete = { id ->
                    viewModel.deleteContact(id)
                }
            )
            DeleteConfirmationDialog(showDialog, {
                showDialog = false
            }, {
                viewModel.deleteContact(contact.id)
            })
        }
    }
}

@Composable
fun DeleteConfirmationDialog(showDialog: Boolean, onDismiss: () -> Unit, onConfirm: () -> Unit) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Delete Contact") },
            text = { Text("Are you sure you want to delete this contact?") },
            confirmButton = {
                Button(onClick = {
                    onConfirm()
                    onDismiss()
                }) {
                    Text("Delete")
                }
            },
            dismissButton = {
                Button(onClick = onDismiss) {
                    Text("Cancel")
                }
            }
        )
    }
}