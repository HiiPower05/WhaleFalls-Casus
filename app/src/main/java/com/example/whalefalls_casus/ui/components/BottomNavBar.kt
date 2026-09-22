package com.example.whalefalls_casus.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.PeopleOutline
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whalefalls_casus.theme.CreamBackground
import com.example.whalefalls_casus.theme.DeepGreen
import com.example.whalefalls_casus.theme.SageGreen

enum class NavigationTab(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    DISCOVER("Discover", Icons.Filled.Explore, Icons.Outlined.Explore),
    SAVED("Saved", Icons.Filled.Bookmark, Icons.Outlined.BookmarkBorder),
    ALERTS("Alerts", Icons.Filled.Notifications, Icons.Outlined.NotificationsNone),
    CONTACTS("Contacts", Icons.Filled.People, Icons.Outlined.PeopleOutline),
    SETTINGS("Settings", Icons.Filled.Settings, Icons.Outlined.Settings)
}

@Composable
fun BottomNavBar(
    currentTab: NavigationTab,
    onTabSelected: (NavigationTab) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = CreamBackground,
        tonalElevation = 8.dp
    ) {
        NavigationTab.entries.forEach { tab ->
            val isSelected = currentTab == tab
            NavigationBarItem(
                selected = isSelected,
                onClick = { onTabSelected(tab) },
                icon = {
                    Icon(
                        imageVector = if (isSelected) tab.selectedIcon else tab.unselectedIcon,
                        contentDescription = tab.title,
                        tint = if (isSelected) DeepGreen else SageGreen
                    )
                },
                label = {
                    Text(
                        text = tab.title,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) DeepGreen else SageGreen
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = SageGreen.copy(alpha = 0.2f)
                )
            )
        }
    }
}
