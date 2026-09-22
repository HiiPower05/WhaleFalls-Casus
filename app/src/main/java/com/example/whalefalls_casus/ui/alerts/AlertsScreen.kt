package com.example.whalefalls_casus.ui.alerts

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Park
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Thunderstorm
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Park
import androidx.compose.material.icons.outlined.Thunderstorm
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
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
import com.example.whalefalls_casus.theme.AlertAmberBg
import com.example.whalefalls_casus.theme.AlertAmberText
import com.example.whalefalls_casus.theme.AlertBlueBg
import com.example.whalefalls_casus.theme.AlertBlueText
import com.example.whalefalls_casus.theme.AlertGreenBg
import com.example.whalefalls_casus.theme.AlertGreenText
import com.example.whalefalls_casus.theme.AlertRedBg
import com.example.whalefalls_casus.theme.AlertRedText
import com.example.whalefalls_casus.theme.CardSurface
import com.example.whalefalls_casus.theme.CreamBackground
import com.example.whalefalls_casus.theme.DeepGreen
import com.example.whalefalls_casus.theme.SageGreen
import com.example.whalefalls_casus.theme.SurfaceBorder
import com.example.whalefalls_casus.theme.TextMuted
import com.example.whalefalls_casus.theme.TextPrimary
import com.example.whalefalls_casus.theme.TextSecondary
import com.example.whalefalls_casus.ui.components.WhaleFallsTopAppBar

@Composable
fun AlertsScreen() {
    var selectedFilter by remember { mutableStateOf("All") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CreamBackground)
    ) {
        WhaleFallsTopAppBar()

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            // Title Header & Settings
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Alerts",
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp,
                            color = DeepGreen
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Stay informed about trail conditions, weather, and closures.",
                            fontSize = 13.sp,
                            color = TextSecondary
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = CardSurface,
                        border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(imageVector = Icons.Default.Settings, contentDescription = null, tint = DeepGreen, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "Alert Settings", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DeepGreen)
                        }
                    }
                }
            }

            // Filter Pills with Badges
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item { AlertFilterPill("All Alerts", count = "12", isSelected = selectedFilter == "All", onClick = { selectedFilter = "All" }) }
                    item { AlertFilterPill("Trail Conditions", count = "6", isSelected = selectedFilter == "Trail", onClick = { selectedFilter = "Trail" }) }
                    item { AlertFilterPill("Weather", count = "4", isSelected = selectedFilter == "Weather", onClick = { selectedFilter = "Weather" }) }
                    item { AlertFilterPill("Closures", count = "2", isSelected = selectedFilter == "Closures", onClick = { selectedFilter = "Closures" }) }
                }
            }

            // High Priority Section
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "High Priority",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = DeepGreen,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)
                )
            }

            item {
                Box(modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)) {
                    HighPriorityAlertCard(
                        category = "TRAIL CLOSURE",
                        title = "Platteklip Gorge Trail Closed",
                        location = "Table Mountain National Park",
                        desc = "The trail is closed until further notice due to a recent rockfall and safety concerns.",
                        timeInfo = "May 24, 2025  |  Ends until further notice",
                        bgColor = AlertRedBg,
                        borderColor = AlertRedText.copy(alpha = 0.3f),
                        accentColor = AlertRedText
                    )
                }
            }

            item {
                Box(modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)) {
                    HighPriorityAlertCard(
                        category = "SEVERE WEATHER",
                        title = "Strong Winds Expected",
                        location = "Western Cape Mountains",
                        desc = "Strong south-easterly winds expected this weekend. Be prepared for changing conditions.",
                        timeInfo = "May 24 – May 25, 2025  |  Active now",
                        bgColor = AlertAmberBg,
                        borderColor = AlertAmberText.copy(alpha = 0.3f),
                        accentColor = AlertAmberText
                    )
                }
            }

            // Recent Alerts Section
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Recent Alerts",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = DeepGreen,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)
                )
            }

            item {
                Column(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    RecentAlertCard(
                        category = "TRAIL CONDITIONS",
                        title = "Muddy Conditions on Skeleton Gorge",
                        location = "Table Mountain National Park",
                        time = "May 23, 2025\n1:30 PM",
                        icon = Icons.Outlined.Park,
                        iconBg = AlertGreenBg,
                        iconTint = AlertGreenText
                    )
                    RecentAlertCard(
                        category = "TRAIL CONDITIONS",
                        title = "Fallen Tree on Nursery Ravine",
                        location = "Newlands Forest",
                        time = "May 23, 2025\n10:15 AM",
                        icon = Icons.Outlined.Park,
                        iconBg = AlertGreenBg,
                        iconTint = AlertGreenText
                    )
                    RecentAlertCard(
                        category = "WEATHER UPDATE",
                        title = "Rain Expected This Week",
                        location = "Western Cape",
                        time = "May 22, 2025\n6:45 PM",
                        icon = Icons.Outlined.Thunderstorm,
                        iconBg = AlertBlueBg,
                        iconTint = AlertBlueText
                    )
                    RecentAlertCard(
                        category = "GENERAL",
                        title = "New Safety Guidelines",
                        location = "Updated hiking safety guidelines now available.",
                        time = "May 22, 2025\n2:00 PM",
                        icon = Icons.Default.Notifications,
                        iconBg = SageGreen.copy(alpha = 0.2f),
                        iconTint = DeepGreen
                    )
                }
            }

            // Enable Location Alerts Banner
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = CardSurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder),
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = DeepGreen,
                            modifier = Modifier.size(44.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(imageVector = Icons.Outlined.LocationOn, contentDescription = null, tint = Color.White)
                            }
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = "Enable Location Alerts", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = DeepGreen)
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(text = "Get real-time alerts for trails and conditions near your current location.", fontSize = 11.sp, color = TextSecondary)
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Button(
                            onClick = { },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = DeepGreen, contentColor = Color.White)
                        ) {
                            Text(text = "Enable Location", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AlertFilterPill(
    title: String,
    count: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        color = if (isSelected) DeepGreen else CardSurface,
        border = if (isSelected) null else androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontSize = 13.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = if (isSelected) Color.White else DeepGreen
            )
            Spacer(modifier = Modifier.width(6.dp))
            Surface(
                shape = CircleShape,
                color = if (isSelected) Color.White.copy(alpha = 0.25f) else SurfaceBorder
            ) {
                Text(
                    text = count,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isSelected) Color.White else DeepGreen,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
        }
    }
}

@Composable
fun HighPriorityAlertCard(
    category: String,
    title: String,
    location: String,
    desc: String,
    timeInfo: String,
    bgColor: Color,
    borderColor: Color,
    accentColor: Color
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        border = androidx.compose.foundation.BorderStroke(1.dp, borderColor),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = Icons.Outlined.Warning,
                contentDescription = null,
                tint = accentColor,
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = category, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = accentColor)
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = title, fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = TextPrimary)
                Spacer(modifier = Modifier.height(2.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Outlined.LocationOn, contentDescription = null, tint = SageGreen, modifier = Modifier.size(12.dp))
                    Text(text = location, fontSize = 11.sp, color = TextSecondary)
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = desc, fontSize = 12.sp, color = TextPrimary, lineHeight = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = timeInfo, fontSize = 11.sp, color = TextMuted)
            }

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = null,
                tint = accentColor,
                modifier = Modifier
                    .size(14.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun RecentAlertCard(
    category: String,
    title: String,
    location: String,
    time: String,
    icon: ImageVector,
    iconBg: Color,
    iconTint: Color
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = CardSurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = CircleShape,
                color = iconBg,
                modifier = Modifier.size(42.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(imageVector = icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(20.dp))
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = category, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = SageGreen)
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = title, fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = DeepGreen)
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = location, fontSize = 11.sp, color = TextSecondary)
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column(horizontalAlignment = Alignment.End) {
                Text(text = time, fontSize = 10.sp, color = TextMuted, lineHeight = 13.sp)
            }

            Spacer(modifier = Modifier.width(6.dp))

            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos, contentDescription = null, tint = SageGreen, modifier = Modifier.size(12.dp))
        }
    }
}
