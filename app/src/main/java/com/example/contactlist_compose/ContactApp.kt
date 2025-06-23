package com.example.contactlist_compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun ContactApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "contactList") {
        composable(route = "contactList") {
            ContactListScreen(navController = navController)
        }
        composable(
            route = "certainContact" + "/{contactId}",
            arguments = listOf(navArgument("contactId") { type = NavType.IntType })
        ) { backStackEntry ->
            val contactId = requireNotNull(backStackEntry.arguments?.getInt("contactId"))
            ContactDetailScreen(contactId = contactId, navController = navController)
        }
    }

}