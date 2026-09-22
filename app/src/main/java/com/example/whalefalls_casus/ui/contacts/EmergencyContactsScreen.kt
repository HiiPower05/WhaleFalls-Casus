package com.example.whalefalls_casus.ui.contacts

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.LocalHospital
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.NearMe
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Policy
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.whalefalls_casus.theme.AlertGreenBg
import com.example.whalefalls_casus.theme.AlertGreenText
import com.example.whalefalls_casus.theme.AlertRedBg
import com.example.whalefalls_casus.theme.AlertRedText
import com.example.whalefalls_casus.theme.CardSurface
import com.example.whalefalls_casus.theme.CreamBackground
import com.example.whalefalls_casus.theme.DeepGreen
import com.example.whalefalls_casus.theme.SageGreen
import com.example.whalefalls_casus.theme.SurfaceBorder
import com.example.whalefalls_casus.theme.TextPrimary
import com.example.whalefalls_casus.theme.TextSecondary
import com.example.whalefalls_casus.ui.components.WhaleFallsTopAppBar

data class ContactDisplayItem(
    val id: Long = 0,
    val name: String,
    val relationship: String,
    val phone: String,
    val avatarInitial: String,
    val avatarBg: Color
)

data class ImportantNumberItem(
    val name: String,
    val number: String,
    val icon: ImageVector
)

@Composable
fun EmergencyContactsScreen() {
    var showAddDialog by remember { mutableStateOf(false) }

    val myContacts = listOf(
        ContactDisplayItem(1, "Michael Roberts", "Partner", "+27 82 123 4567", "M", Color(0xFF1F3A2E)),
        ContactDisplayItem(2, "Anna Roberts", "Sister", "+27 71 987 6543", "A", Color(0xFF567D73)),
        ContactDisplayItem(3, "Dr. James Wilson", "Family Doctor", "+27 21 555 1234", "D", Color(0xFF4A7C59)),
        ContactDisplayItem(4, "Table Mountain Search & Rescue", "Rescue Service", "+27 21 937 0300", "T", Color(0xFF385E72))
    )

    val importantNumbers = listOf(
        ImportantNumberItem("SANParks Emergency", "+27 12 426 5000", Icons.Outlined.Shield),
        ImportantNumberItem("ER24 Emergency Medical Services", "084 124", Icons.Outlined.LocalHospital),
        ImportantNumberItem("South African Police Service", "10111", Icons.Outlined.Policy),
        ImportantNumberItem("Mountain Club of South Africa", "+27 21 424 1511", Icons.Outlined.Shield)
    )

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
            // Header
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Emergency Contacts",
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 26.sp,
                            color = DeepGreen
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Manage your emergency contacts and quick access numbers.",
                            fontSize = 13.sp,
                            color = TextSecondary
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = { showAddDialog = true },
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = DeepGreen, contentColor = Color.White)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "Add Contact", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            // Quick Emergency Actions Section
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Quick Emergency Actions",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = DeepGreen,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Call Emergency Services 112 Box
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = AlertRedBg),
                        border = androidx.compose.foundation.BorderStroke(1.dp, AlertRedText.copy(alpha = 0.2f)),
                        modifier = Modifier.weight(1f)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Surface(shape = CircleShape, color = AlertRedText, modifier = Modifier.size(36.dp)) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(imageVector = Icons.Filled.Call, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "Call Emergency Services", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = AlertRedText)
                            Text(text = "112", fontWeight = FontWeight.Bold, fontSize = 22.sp, color = AlertRedText)
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(text = "Police · Ambulance · Fire", fontSize = 10.sp, color = TextSecondary)
                        }
                    }

                    // Share My Location Box
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = AlertGreenBg),
                        border = androidx.compose.foundation.BorderStroke(1.dp, AlertGreenText.copy(alpha = 0.2f)),
                        modifier = Modifier.weight(1f)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Surface(shape = CircleShape, color = AlertGreenText, modifier = Modifier.size(36.dp)) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(imageVector = Icons.Outlined.NearMe, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "Share My Location", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = AlertGreenText)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = "Send your location to emergency contacts", fontSize = 11.sp, color = TextSecondary, lineHeight = 15.sp)
                        }
                    }
                }
            }

            // My Emergency Contacts Section
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "My Emergency Contacts",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = DeepGreen,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)
                )
            }

            items(myContacts) { contact ->
                Box(modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp)) {
                    ContactCardItem(contact = contact)
                }
            }

            // Other Important Numbers Section
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Other Important Numbers",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = DeepGreen,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)
                )

                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = CardSurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder),
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)
                ) {
                    Column(modifier = Modifier.padding(vertical = 4.dp)) {
                        importantNumbers.forEachIndexed { index, item ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Surface(shape = CircleShape, color = SageGreen.copy(alpha = 0.15f), modifier = Modifier.size(34.dp)) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Icon(imageVector = item.icon, contentDescription = null, tint = DeepGreen, modifier = Modifier.size(16.dp))
                                    }
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(text = item.name, fontWeight = FontWeight.SemiBold, fontSize = 13.sp, color = TextPrimary, modifier = Modifier.weight(1f))
                                Text(text = item.number, fontSize = 13.sp, fontWeight = FontWeight.Medium, color = TextSecondary)
                                Spacer(modifier = Modifier.width(8.dp))
                                Icon(imageVector = Icons.Outlined.Phone, contentDescription = null, tint = DeepGreen, modifier = Modifier.size(18.dp))
                            }
                            if (index < importantNumbers.size - 1) {
                                Divider(color = SurfaceBorder, modifier = Modifier.padding(horizontal = 16.dp))
                            }
                        }
                    }
                }
            }

            // Stay Safe Tip Box
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
                        Surface(shape = CircleShape, color = SageGreen.copy(alpha = 0.2f), modifier = Modifier.size(42.dp)) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(imageVector = Icons.Outlined.Shield, contentDescription = null, tint = DeepGreen)
                            }
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = "Stay Safe", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = DeepGreen)
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(text = "Always inform someone of your hiking plans and expected return time. Keep your phone charged and carry a power bank.", fontSize = 11.sp, color = TextSecondary, lineHeight = 15.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ContactCardItem(contact: ContactDisplayItem) {
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
                color = contact.avatarBg,
                modifier = Modifier.size(44.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(text = contact.avatarInitial, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = contact.name, fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = DeepGreen)
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = contact.relationship, fontSize = 11.sp, color = TextSecondary)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Outlined.Phone, contentDescription = null, tint = SageGreen, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = contact.phone, fontSize = 12.sp, color = TextPrimary)
                Spacer(modifier = Modifier.width(12.dp))
                Icon(imageVector = Icons.Outlined.ChatBubbleOutline, contentDescription = null, tint = DeepGreen, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(10.dp))
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = null, tint = SageGreen, modifier = Modifier.size(18.dp))
            }
        }
    }
}
