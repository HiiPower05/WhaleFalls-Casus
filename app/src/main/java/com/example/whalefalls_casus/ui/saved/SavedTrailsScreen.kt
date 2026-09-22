package com.example.whalefalls_casus.ui.saved

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.CloudDownload
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whalefalls_casus.theme.CardSurface
import com.example.whalefalls_casus.theme.CreamBackground
import com.example.whalefalls_casus.theme.DeepGreen
import com.example.whalefalls_casus.theme.DifficultRed
import com.example.whalefalls_casus.theme.EasyGreen
import com.example.whalefalls_casus.theme.ModerateOrange
import com.example.whalefalls_casus.theme.SageGreen
import com.example.whalefalls_casus.theme.SurfaceBorder
import com.example.whalefalls_casus.theme.TextPrimary
import com.example.whalefalls_casus.theme.TextSecondary
import com.example.whalefalls_casus.ui.components.WhaleFallsTopAppBar

data class SavedTrailItem(
    val id: String,
    val name: String,
    val location: String,
    val distance: String,
    val duration: String,
    val elevation: String,
    val rating: String,
    val reviews: String,
    val difficulty: String,
    val difficultyColor: Color,
    val isDownloaded: Boolean
)

@Composable
fun SavedTrailsScreen(
    onTrailClick: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("All") }
    var isListView by remember { mutableStateOf(true) }

    val savedTrails = listOf(
        SavedTrailItem("lions_head", "Lion's Head", "Cape Town, Western Cape", "5.5 km", "~2 h", "669 m", "4.8", "(2.3k reviews)", "MODERATE", ModerateOrange, true),
        SavedTrailItem("kloof_corner", "Kloof Corner Ridge", "Table Mountain, WC", "3.1 km", "~1.5 h", "470 m", "4.7", "(1.1k reviews)", "MODERATE", ModerateOrange, true),
        SavedTrailItem("swartberg_pass", "Swartberg Pass", "Oudtshoorn, Western Cape", "12.4 km", "~5 h", "1,230 m", "4.9", "(856 reviews)", "DIFFICULT", DifficultRed, true),
        SavedTrailItem("silvermine", "Silvermine Loop", "Noordhoek, Western Cape", "6.2 km", "~2 h", "420 m", "4.6", "(612 reviews)", "EASY", EasyGreen, false),
        SavedTrailItem("devils_peak", "Devil's Peak", "Cape Town, Western Cape", "7.0 km", "~3 h", "1,009 m", "4.6", "(732 reviews)", "MODERATE", ModerateOrange, false)
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
            // Header with view toggle
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Saved Trails",
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 26.sp,
                            color = DeepGreen
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Your saved trails and routes, all in one place.",
                            fontSize = 13.sp,
                            color = TextSecondary
                        )
                    }

                    // View Toggle Buttons (List / Grid)
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = CardSurface,
                        border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder)
                    ) {
                        Row(modifier = Modifier.padding(2.dp)) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (isListView) DeepGreen else Color.Transparent,
                                modifier = Modifier.size(34.dp)
                            ) {
                                IconButton(onClick = { isListView = true }) {
                                    Icon(imageVector = Icons.Default.ViewList, contentDescription = "List View", tint = if (isListView) Color.White else SageGreen, modifier = Modifier.size(18.dp))
                                }
                            }
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (!isListView) DeepGreen else Color.Transparent,
                                modifier = Modifier.size(34.dp)
                            ) {
                                IconButton(onClick = { isListView = false }) {
                                    Icon(imageVector = Icons.Default.GridView, contentDescription = "Grid View", tint = if (!isListView) Color.White else SageGreen, modifier = Modifier.size(18.dp))
                                }
                            }
                        }
                    }
                }
            }

            // Filter Pills
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item { FilterPill("All", icon = Icons.Filled.Bookmark, isSelected = selectedFilter == "All", onClick = { selectedFilter = "All" }) }
                    item { FilterPill("Downloaded", icon = Icons.Outlined.CloudDownload, isSelected = selectedFilter == "Downloaded", onClick = { selectedFilter = "Downloaded" }) }
                    item { FilterPill("Favorites", icon = Icons.Outlined.StarBorder, isSelected = selectedFilter == "Favorites", onClick = { selectedFilter = "Favorites" }) }
                    item { FilterPill("Completed", icon = Icons.Filled.Check, isSelected = selectedFilter == "Completed", onClick = { selectedFilter = "Completed" }) }
                }
            }

            // Search & Sort bar
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text("Search saved trails...", fontSize = 13.sp) },
                        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = SageGreen) },
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = CardSurface,
                            unfocusedContainerColor = CardSurface,
                            focusedBorderColor = DeepGreen,
                            unfocusedBorderColor = SurfaceBorder
                        ),
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = CardSurface,
                        border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder),
                        modifier = Modifier.height(54.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp).fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(imageVector = Icons.Default.FilterList, contentDescription = null, tint = DeepGreen, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "Sort by", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = DeepGreen)
                            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null, tint = DeepGreen, modifier = Modifier.size(16.dp))
                        }
                    }
                }
            }

            // Saved Trails List Items
            items(savedTrails) { trail ->
                Box(modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)) {
                    SavedTrailCardItem(trail = trail, onClick = { onTrailClick(trail.id) })
                }
            }

            // Offline Access Banner
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
                            color = SageGreen.copy(alpha = 0.2f),
                            modifier = Modifier.size(44.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(imageVector = Icons.Outlined.CloudDownload, contentDescription = null, tint = DeepGreen)
                            }
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = "Access your trails anywhere", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = DeepGreen)
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(text = "Downloaded trails are available offline, even without a signal.", fontSize = 11.sp, color = TextSecondary)
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Button(
                            onClick = { },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = DeepGreen, contentColor = Color.White)
                        ) {
                            Text(text = "Manage Downloads", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FilterPill(
    text: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
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
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isSelected) Color.White else SageGreen,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = text,
                fontSize = 13.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = if (isSelected) Color.White else DeepGreen
            )
        }
    }
}

@Composable
fun SavedTrailCardItem(
    trail: SavedTrailItem,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardSurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Photo Canvas Thumbnail
            Box(
                modifier = Modifier
                    .size(85.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(DeepGreen)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawCircle(color = SageGreen, radius = size.width * 0.7f)
                }
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = trail.difficultyColor,
                    modifier = Modifier
                        .padding(6.dp)
                        .align(Alignment.BottomStart)
                ) {
                    Text(text = trail.difficulty, fontSize = 8.sp, fontWeight = FontWeight.Bold, color = Color.White, modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp))
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = trail.name, fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = DeepGreen)

                    if (trail.isDownloaded) {
                        Surface(shape = RoundedCornerShape(10.dp), color = EasyGreen.copy(alpha = 0.15f)) {
                            Row(
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = EasyGreen, modifier = Modifier.size(12.dp))
                                Text(text = " DOWNLOADED", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = EasyGreen)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(2.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Outlined.LocationOn, contentDescription = null, tint = SageGreen, modifier = Modifier.size(12.dp))
                    Text(text = trail.location, fontSize = 11.sp, color = TextSecondary)
                }

                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "${trail.distance}  •  ${trail.duration}  •  ${trail.elevation}", fontSize = 11.sp, color = TextPrimary)

                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = Color(0xFFF39C12), modifier = Modifier.size(13.dp))
                        Text(text = " ${trail.rating} ${trail.reviews}", fontSize = 11.sp, color = TextSecondary)
                    }

                    Row {
                        Icon(imageVector = Icons.Filled.Bookmark, contentDescription = null, tint = DeepGreen, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = null, tint = SageGreen, modifier = Modifier.size(18.dp))
                    }
                }
            }
        }
    }
}
