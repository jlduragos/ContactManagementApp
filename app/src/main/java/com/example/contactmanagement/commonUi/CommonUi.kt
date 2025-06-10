package com.example.contactmanagement.commonUi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.contactmanagement.model.ContactItem
import com.example.contactmanagement.screens.ContactViewModel

@Composable
fun ContactItem(contact: ContactItem, onEdit: (id: Int) -> Unit, onDelete: (id: Int) -> Unit) {
    Row {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = contact.name,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = contact.phoneNumber,
                style = MaterialTheme.typography.bodySmall
            )

        }
        Button(
            onClick = { onEdit(contact.id) },
            modifier = Modifier
                .padding(8.dp)
        ) {
            Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.Edit,
                contentDescription = "Edit Contact"
            )
        }
        Button(
            onClick = { onDelete(contact.id) },
            modifier = Modifier
                .padding(8.dp)
        ) {
            Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.Delete,
                contentDescription = "Delete Contact"
            )
        }
    }
}

@Composable
fun ContactInputs(
    navController: NavController,
    onSaveClicked: (name: String, phoneNumber: String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text("Name")
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = phoneNumber,
            onValueChange = {
                phoneNumber = it
            },
            label = {
                Text("Phone Number")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            onClick = {
                onSaveClicked(name, phoneNumber)
                navController.navigate("contacts")
            }
        ) {
            Text("Save Contact")
        }
    }

}
