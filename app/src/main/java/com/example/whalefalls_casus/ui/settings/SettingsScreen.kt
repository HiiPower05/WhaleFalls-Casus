package com.example.whalefalls_casus.ui.settings

// for unit measurement
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.runtime.saveable.rememberSaveable

import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import com.example.whalefalls_casus.data.preferences.UserPreferences

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.outlined.CloudUpload
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Shield

import androidx.compose.material.icons.outlined.Timeline

import androidx.compose.material3.Card

import androidx.compose.material3.CardDefaults

import androidx.compose.material3.Divider

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.OutlinedButton

import androidx.compose.material3.Surface

import androidx.compose.material3.Switch

import androidx.compose.material3.SwitchDefaults

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue

import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.remember

import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.graphics.vector.ImageVector

import androidx.compose.ui.text.font.FontFamily

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp

import androidx.compose.ui.unit.sp

import com.example.whalefalls_casus.model.User

import com.example.whalefalls_casus.theme.CardSurface

import com.example.whalefalls_casus.theme.CreamBackground

import com.example.whalefalls_casus.theme.DeepGreen

import com.example.whalefalls_casus.theme.DifficultRed

import com.example.whalefalls_casus.theme.SageGreen

import com.example.whalefalls_casus.theme.SurfaceBorder

import com.example.whalefalls_casus.theme.TextPrimary

import com.example.whalefalls_casus.theme.TextSecondary

import com.example.whalefalls_casus.ui.components.WhaleFallsTopAppBar

// Change for part 3. Measurement
data class SettingsRowItem(
    val title: String,
    val subtitle: String? = null,
    val trailingValue: String? = null,
    val icon: ImageVector,
    val hasSwitch: Boolean = false,
    val isSwitchOn: Boolean = false,
    val onClick: () -> Unit = {}
)

@Composable

fun SettingsScreen(

    user: User,

    onSignOutClick: () -> Unit = {}

) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val trackingEnabled by UserPreferences
        .getTrackingEnabled(context)
        .collectAsState(initial = true)

    // Measurement for part 3

    val selectedUnits by UserPreferences
        .getUnits(context)
        .collectAsState(initial = "Metric")

    val selectedLanguage by UserPreferences
        .getLanguage(context)
        .collectAsState(initial = "English")

    val darkTheme by UserPreferences
        .getDarkTheme(context)
        .collectAsState(initial = false)

    var showUnitsDialog by rememberSaveable {
        mutableStateOf(false)
    }

    var showLanguageDialog by rememberSaveable {
        mutableStateOf(false)
    }
    var showAppearanceDialog by rememberSaveable {
        mutableStateOf(false)
    }

    if (showAppearanceDialog) {
        AlertDialog(
            onDismissRequest = { showAppearanceDialog = false },
            title = { Text("Appearance") },
            text = {
                Column {
                    TextButton(
                        onClick = {
                            scope.launch {
                                UserPreferences.saveDarkTheme(context, false)
                            }
                            showAppearanceDialog = false
                        }
                    ) {
                        Text("Light mode")
                    }

                    TextButton(
                        onClick = {
                            scope.launch {
                                UserPreferences.saveDarkTheme(context, true)
                            }
                            showAppearanceDialog = false
                        }
                    ) {
                        Text("Dark mode")
                    }
                }
            },
            confirmButton = {}
        )
    }

    Column(

        modifier = Modifier

            .fillMaxSize()

            .background(MaterialTheme.colorScheme.background)
    ) {
        // Measurements.
        if (showUnitsDialog) {
            AlertDialog(
                onDismissRequest = { showUnitsDialog = false },
                title = { Text("Select Units") },
                text = {
                    Column {
                        listOf("Metric", "Imperial").forEach { unit ->
                            TextButton(
                                onClick = {
                                    scope.launch {
                                        UserPreferences.saveUnits(context, unit)
                                    }
                                    showUnitsDialog = false
                                }
                            ) {
                                Text(
                                    if (selectedUnits == unit)
                                        "✓ $unit"
                                    else unit
                                )
                            }
                        }
                    }
                },
                confirmButton = {}
            )
        }

        if (showLanguageDialog) {
            AlertDialog(
                onDismissRequest = { showLanguageDialog = false },
                title = { Text("Select Language") },
                text = {
                    Column {
                        listOf(
                            "English",
                            "Afrikaans",
                            "isiXhosa"
                        ).forEach { language ->
                            TextButton(
                                onClick = {
                                    scope.launch {
                                        UserPreferences.saveLanguage(context, language)
                                    }
                                    showLanguageDialog = false
                                }
                            ) {
                                Text(
                                    if (selectedLanguage == language)
                                        "✓ $language"
                                    else language
                                )
                            }
                        }
                    }
                },
                confirmButton = {}
            )
        }

        WhaleFallsTopAppBar()



        LazyColumn(

            modifier = Modifier.fillMaxSize(),

            contentPadding = PaddingValues(bottom = 28.dp)

        ) {
// Header
            item {

                Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)) {

                    Text(

                        text = "Settings",

                        fontFamily = FontFamily.Serif,

                        fontWeight = FontWeight.Bold,

                        fontSize = 28.sp,

                        color = MaterialTheme.colorScheme.onBackground

                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(

                        text = "Manage your preferences and app settings.",

                        fontSize = 13.sp,

                        color = MaterialTheme.colorScheme.onSurfaceVariant

                    )

                }

            }

// User Profile Card

            item {

                Card(

                    shape = RoundedCornerShape(16.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),

                    border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder),

                    modifier = Modifier

                        .padding(horizontal = 20.dp, vertical = 6.dp)

                        .clickable { }

                ) {

                    Row(

                        modifier = Modifier

                            .fillMaxWidth()

                            .padding(16.dp),

                        verticalAlignment = Alignment.CenterVertically

                    ) {

                        Surface(
                            shape = CircleShape, color = DeepGreen, modifier = Modifier.size(48.dp)
                        ) {

                            Box(contentAlignment = Alignment.Center) {

                                Text(
                                    text = user.fullName
                                        .split(" ")
                                        .mapNotNull { it.firstOrNull()?.toString() }
                                        .take(2)
                                        .joinToString("")
                                        .uppercase()
                                        .ifBlank { "U" },
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )

                            }

                        }



                        Spacer(modifier = Modifier.width(14.dp))



                        Column(modifier = Modifier.weight(1f)) {

                            Text(
                                text = user.fullName.ifBlank { "User" },
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            Spacer(modifier = Modifier.height(2.dp))

                            Text(
                                text = user.email.ifBlank { "No email available" },
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                        }



                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(14.dp)
                        )

                    }

                }

            }

// Section 1: Preferences

            item {

                SettingsGroup(

                    groupTitle = "Preferences",

                    items = listOf(

                        SettingsRowItem(
                            "Units",
                            subtitle = selectedUnits,
                            icon = Icons.Outlined.Map,
                            onClick = {
                                showUnitsDialog = true
                            }
                        ),

                        SettingsRowItem(
                            "Language",
                            subtitle = selectedLanguage,
                            icon = Icons.Outlined.Language,
                            onClick = {
                                showLanguageDialog = true
                            }
                        ),

                        SettingsRowItem(
                            "Location & Permissions",
                            subtitle = "Manage location access and permissions",
                            icon = Icons.Outlined.LocationOn
                        ),

                        SettingsRowItem(
                            "Notifications",
                            subtitle = "Manage alert and notification preferences",
                            icon = Icons.Outlined.Notifications
                        ),

                        SettingsRowItem(
                            "Offline Maps",
                            subtitle = "Manage downloaded maps",
                            icon = Icons.Outlined.FileDownload
                        ),

                        SettingsRowItem(
                            "Appearance",
                            subtitle = if (darkTheme) "Dark mode" else "Light mode",
                            icon = Icons.Outlined.DarkMode,
                            onClick = { showAppearanceDialog = true }
                        )

                    )

                )

            }

// Section 2: Safety & Tracking

            item {

                SettingsGroup(

                    groupTitle = "Safety & Tracking",

                    items = listOf(

                        SettingsRowItem(
                            "Safety Features",
                            subtitle = "Location sharing, emergency, and safety settings",
                            icon = Icons.Outlined.Shield
                        ),

                        SettingsRowItem(
                            "Activity Tracking",
                            subtitle = "Manage tracking preferences",
                            icon = Icons.Outlined.Timeline,
                            hasSwitch = true,
                            isSwitchOn = trackingEnabled
                        )

                    ),

                    onSwitchToggle = { enabled ->
                        scope.launch {
                            UserPreferences.saveTrackingEnabled(context, enabled)
                        }
                    }

                )

            }

// Section 3: Account & Data

            item {

                SettingsGroup(

                    groupTitle = "Account & Data",

                    items = listOf(

                        SettingsRowItem(
                            "Account Settings",
                            subtitle = "Update your account information",
                            icon = Icons.Outlined.Person
                        ),

                        SettingsRowItem(
                            "Backup & Restore",
                            subtitle = "Back up or restore your data",
                            icon = Icons.Outlined.CloudUpload
                        ),

                        SettingsRowItem(
                            "Clear Cache",
                            subtitle = "Free up storage space",
                            trailingValue = "45.2 MB",
                            icon = Icons.Outlined.Delete
                        )

                    )

                )

            }

// Section 4: About

            item {

                SettingsGroup(

                    groupTitle = "About",

                    items = listOf(

                        SettingsRowItem(
                            "About WhaleFalls CASUS",
                            subtitle = "Version 1.2.3",
                            icon = Icons.Outlined.Info
                        ),

                        SettingsRowItem("Terms of Service", icon = Icons.Outlined.Description),

                        SettingsRowItem("Privacy Policy", icon = Icons.Outlined.Shield)

                    )

                )

            }

// Sign Out Button

            item {

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedButton(

                    onClick = onSignOutClick,

                    shape = RoundedCornerShape(12.dp),

                    border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder),

                    modifier = Modifier

                        .fillMaxWidth()

                        .padding(horizontal = 20.dp)

                        .height(48.dp)

                ) {

                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ExitToApp,
                            contentDescription = null,
                            tint = DifficultRed,
                            modifier = Modifier.size(18.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Sign Out",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = DifficultRed
                        )

                    }

                }

            }

        }

    }

}

@Composable

fun SettingsGroup(

    groupTitle: String,

    items: List<SettingsRowItem>,

    onSwitchToggle: (Boolean) -> Unit = {}

) {

    Column {

        Text(

            text = groupTitle,

            fontFamily = FontFamily.Serif,

            fontWeight = FontWeight.Bold,

            fontSize = 14.sp,

            color = MaterialTheme.colorScheme.onBackground,

            modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 6.dp)

        )



        Card(

            shape = RoundedCornerShape(16.dp),

            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),

            border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder),

            modifier = Modifier.padding(horizontal = 20.dp)

        ) {

            Column {

                items.forEachIndexed { index, rowItem ->

                    Row(

                        modifier = Modifier

                            .fillMaxWidth()
                            // Changed. 04/11
                            .clickable {
                                if (!rowItem.hasSwitch) {
                                    rowItem.onClick()
                                }
                            }

                            .padding(horizontal = 16.dp, vertical = 12.dp),

                        verticalAlignment = Alignment.CenterVertically

                    ) {

                        Icon(
                            imageVector = rowItem.icon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(20.dp)
                        )

                        Spacer(modifier = Modifier.width(14.dp))



                        Column(modifier = Modifier.weight(1f)) {

                            Text(
                                text = rowItem.title,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onSurface

                            )

                            if (rowItem.subtitle != null) {

                                Spacer(modifier = Modifier.height(2.dp))

                                Text(
                                    text = rowItem.subtitle,
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )

                            }

                        }



                        if (rowItem.trailingValue != null) {

                            Text(
                                text = rowItem.trailingValue,
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                        }



                        if (rowItem.hasSwitch) {

                            Switch(

                                checked = rowItem.isSwitchOn,

                                onCheckedChange = onSwitchToggle,

                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White, checkedTrackColor = DeepGreen
                                )

                            )

                        } else {

                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                contentDescription = null,
                                tint = SageGreen,
                                modifier = Modifier.size(12.dp)
                            )

                        }

                    }



                    if (index < items.size - 1) {

                        Divider(
                            color = SurfaceBorder, modifier = Modifier.padding(horizontal = 16.dp)
                        )

                    }

                }

            }

        }

    }

}

