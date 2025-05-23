package com.example.android

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebaseauth.viewmodel.AuthViewModel
import com.example.firebaseauth.viewmodel.NotesViewModel
import com.example.android.routes.Routes
import com.example.android.views.HomePage
import com.example.android.views.LoginPage
import com.example.android.views.SignupPage
import com.example.android.views.EditNotePage
import com.example.firebaseauth.viewmodel.AuthState
import androidx.compose.runtime.getValue

@Composable
fun MyAppNavigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    val notesViewModel = NotesViewModel()
    val authState by authViewModel.authState.observeAsState()

    val startDestination = when (authState) {
        is AuthState.Authenticated -> Routes.home
        else -> Routes.login
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Routes.login) {
            LoginPage(modifier, navController, authViewModel)
        }
        composable(Routes.signup) {
            SignupPage(modifier, navController, authViewModel)
        }
        composable(Routes.home) {
            HomePage(navController, authViewModel, notesViewModel)
        }
        composable(Routes.edit_note) {
            EditNotePage(navController, notesViewModel, authViewModel)
        }
    }
}
