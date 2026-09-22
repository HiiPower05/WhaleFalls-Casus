package com.example.whalefalls_casus.navigation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.whalefalls_casus.model.User
import com.example.whalefalls_casus.ui.alerts.AlertsScreen
import com.example.whalefalls_casus.ui.auth.CreateAccountScreen
import com.example.whalefalls_casus.ui.auth.LoginScreen
import com.example.whalefalls_casus.ui.components.BottomNavBar
import com.example.whalefalls_casus.ui.components.NavigationTab
import com.example.whalefalls_casus.ui.contacts.EmergencyContactsScreen
import com.example.whalefalls_casus.ui.detail.TrailDetailScreen
import com.example.whalefalls_casus.ui.discover.DiscoverScreen
import com.example.whalefalls_casus.ui.map.TrailMapScreen
import com.example.whalefalls_casus.ui.saved.SavedTrailsScreen
import com.example.whalefalls_casus.ui.settings.SettingsScreen
import com.example.whalefalls_casus.ui.splash.SplashScreen
import com.example.whalefalls_casus.viewmodel.UserViewModel

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object CreateAccount : Screen("create_account")
    object TermsOfService : Screen("terms_of_service")
    object PrivacyPolicy : Screen("privacy_policy")
    object Main : Screen("main")
    object TrailDetail : Screen("trail_detail/{trailId}") {
        fun createRoute(trailId: String) = "trail_detail/$trailId"
    }
    object TrailMap : Screen("trail_map/{trailId}") {
        fun createRoute(trailId: String) = "trail_map/$trailId"
    }
}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    userViewModel: UserViewModel = viewModel(),
    onGoogleSignInClicked: () -> Unit = {}
) {
    val currentUser by userViewModel.currentUser.collectAsState()
    val context = LocalContext.current

    // Automatically navigate to Main screen when single sign-in succeeds
    androidx.compose.runtime.LaunchedEffect(currentUser.isLoggedIn) {
        if (currentUser.isLoggedIn) {
            val currentRoute = navController.currentBackStackEntry?.destination?.route
            if (currentRoute == Screen.Login.route || currentRoute == Screen.CreateAccount.route || currentRoute == Screen.Splash.route) {
                navController.navigate(Screen.Main.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            }
        }
    }
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onTimeout = {
                    val target = if (currentUser.isLoggedIn) Screen.Main.route else Screen.Login.route
                    navController.navigate(target) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Login.route) {
            LoginScreen(
                userViewModel = userViewModel,
                onGoogleSignInClicked = onGoogleSignInClicked,
                onLoginSuccess = { email, password ->
                    userViewModel.loginUser(email, password) { success, error ->
                        if (success) {
                            navController.navigate(Screen.Main.route) {
                                popUpTo(Screen.Login.route) { inclusive = true }
                            }
                        } else {
                            Toast.makeText(context, error ?: "Login failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                onGoogleLogin = { _, _ ->
                    onGoogleSignInClicked()
                },
                onNavigateToSignUp = {
                    navController.navigate(Screen.CreateAccount.route)
                }
            )
        }

        composable(Screen.CreateAccount.route) {
            CreateAccountScreen(
                onGoogleSignInClicked = onGoogleSignInClicked, // Added
                onAccountCreated = { fullName, email, password ->
                    userViewModel.signUpUser(fullName, email, password) { success, error ->
                        if (success) {
                            navController.navigate(Screen.Main.route) {
                                popUpTo(Screen.CreateAccount.route) { inclusive = true }
                            }
                        } else {
                            Toast.makeText(context, error ?: "Sign up failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                onGoogleSignUp = { _, _ -> },
                onNavigateToLogin = {
                    navController.popBackStack()
                },
                onBackClick = {
                    navController.popBackStack()
                },
                onNavigateToTerms = {
                    navController.navigate(Screen.TermsOfService.route)
                },
                onNavigateToPrivacy = {
                    navController.navigate(Screen.PrivacyPolicy.route)
                }
            )
        }

        composable(Screen.TermsOfService.route) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Terms of Service Screen")
            }
        }

        composable(Screen.PrivacyPolicy.route) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Privacy Policy Screen")
            }
        }

        composable(Screen.Main.route) {
            MainAppScreen(
                currentUser = currentUser,
                onTrailClick = { trailId ->
                    navController.navigate(Screen.TrailDetail.createRoute(trailId))
                },
                onSignOutClick = {
                    userViewModel.signOut()
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Main.route) { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = Screen.TrailDetail.route,
            arguments = listOf(navArgument("trailId") { type = NavType.StringType })
        ) { backStackEntry ->
            val trailId = backStackEntry.arguments?.getString("trailId") ?: "lions_head"
            TrailDetailScreen(
                trailId = trailId,
                onBackClick = { navController.popBackStack() },
                onStartNavigationClick = {
                    navController.navigate(Screen.TrailMap.createRoute(trailId))
                }
            )
        }

        composable(
            route = Screen.TrailMap.route,
            arguments = listOf(navArgument("trailId") { type = NavType.StringType })
        ) { backStackEntry ->
            val trailId = backStackEntry.arguments?.getString("trailId") ?: "lions_head"
            TrailMapScreen(
                trailId = trailId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

@Composable
fun MainAppScreen(
    currentUser: User,
    onTrailClick: (String) -> Unit,
    onSignOutClick: () -> Unit
) {
    var selectedTab by remember { mutableStateOf(NavigationTab.DISCOVER) }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                currentTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedTab) {
                NavigationTab.DISCOVER -> DiscoverScreen(onTrailClick = onTrailClick)
                NavigationTab.SAVED -> SavedTrailsScreen(onTrailClick = onTrailClick)
                NavigationTab.ALERTS -> AlertsScreen()
                NavigationTab.CONTACTS -> EmergencyContactsScreen()
                NavigationTab.SETTINGS -> SettingsScreen(
                    user = currentUser,
                    onSignOutClick = onSignOutClick
                )
            }
        }
    }
}