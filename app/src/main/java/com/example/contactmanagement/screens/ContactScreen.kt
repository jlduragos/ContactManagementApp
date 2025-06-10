package com.example.contactmanagement.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.contactmanagement.commonUi.ContactItem

@Composable
fun ContactScreen(viewModel: ContactViewModel, navController: NavController, modifier: Modifier) {
    val contacts by viewModel.contactList.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Contacts")
            if (contacts.isEmpty()) {
                Text(
                    text = "No contacts available. \n Please add a contact.",
                    color = Color.LightGray,
                    modifier = Modifier.padding(16.dp)
                )
            }
            LazyColumn {
                items(
                    items = contacts,
                    key = {
                        it.id
                    }
                ) { contact ->
                    ContactItem(
                        contact = contact,
                        onEdit = { id ->
                            navController.navigate("editContact/$id")
                        },
                        onDelete = {
                            showDialog = true
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
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),
            onClick = {
                navController.navigate("addContact")
            },
            content = {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Contact"
                )
            }
        )
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