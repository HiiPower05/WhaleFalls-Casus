package com.example.whalefalls_casus.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object CreateAccount : Screen("create_account")
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
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onTimeout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onNavigateToSignUp = {
                    navController.navigate(Screen.CreateAccount.route)
                }
            )
        }

        composable(Screen.CreateAccount.route) {
            CreateAccountScreen(
                onAccountCreated = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.CreateAccount.route) { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Main.route) {
            MainAppScreen(
                onTrailClick = { trailId ->
                    navController.navigate(Screen.TrailDetail.createRoute(trailId))
                },
                onSignOutClick = {
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
        androidx.compose.foundation.layout.Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedTab) {
                NavigationTab.DISCOVER -> DiscoverScreen(onTrailClick = onTrailClick)
                NavigationTab.SAVED -> SavedTrailsScreen(onTrailClick = onTrailClick)
                NavigationTab.ALERTS -> AlertsScreen()
                NavigationTab.CONTACTS -> EmergencyContactsScreen()
                NavigationTab.SETTINGS -> SettingsScreen(onSignOutClick = onSignOutClick)
            }
        }
    }
}
