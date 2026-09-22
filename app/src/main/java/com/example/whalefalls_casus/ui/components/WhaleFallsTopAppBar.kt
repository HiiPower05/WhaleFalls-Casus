package com.example.whalefalls_casus.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.whalefalls_casus.theme.CreamBackground
import com.example.whalefalls_casus.theme.DeepGreen

@Composable
fun WhaleFallsTopAppBar(
    onMenuClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(CreamBackground)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Menu / Hamburger
        IconButton(onClick = onMenuClick) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                tint = DeepGreen,
                modifier = Modifier.size(26.dp)
            )
        }

        // Center Logo
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            WhaleFallsLogo(
                whaleHeight = 36.dp,
                compactText = true
            )
        }

        // Notification Bell
        Box {
            IconButton(onClick = onNotificationClick) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = "Notifications",
                    tint = DeepGreen,
                    modifier = Modifier.size(26.dp)
                )
            }
            // Small alert badge
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(Color(0xFFC0392B), shape = CircleShape)
                    .align(Alignment.TopEnd)
                    .padding(top = 8.dp, end = 8.dp)
            )
        }
    }
}
