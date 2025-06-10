package com.example.contactmanagement

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.contactmanagement.model.ContactItem
import com.example.contactmanagement.screens.AddContactScreen
import com.example.contactmanagement.screens.ContactScreen
import com.example.contactmanagement.screens.ContactViewModel
import com.example.contactmanagement.screens.EditScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    viewModel: ContactViewModel,
    modifier: Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = "contacts"
    ) {
        composable("contacts") {
            ContactScreen(viewModel = viewModel, navController = navController, modifier)
        }
        composable("addContact") {
            AddContactScreen(navController = navController, viewModel = viewModel, modifier)
        }
        composable("editContact/{contactId}") { backStackEntry ->
            val contactId = backStackEntry.arguments?.getInt("contactId")
            EditScreen(navController = navController, viewModel = viewModel, contactId = contactId)
        }
    }
}