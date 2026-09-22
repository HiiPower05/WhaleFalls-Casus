package com.example.whalefalls_casus.ui.discover

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material.icons.outlined.Landscape
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Park
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
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

data class TrailCardItem(
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
    val bgGradient: List<Color>
)

@Composable
fun DiscoverScreen(
    onTrailClick: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All Trails") }

    val recommendedTrails = listOf(
        TrailCardItem(
            id = "lions_head",
            name = "Lion's Head",
            location = "Cape Town, Western Cape",
            distance = "5.5 km",
            duration = "~2 h",
            elevation = "669 m",
            rating = "4.8",
            reviews = "(2.3k)",
            difficulty = "MODERATE",
            difficultyColor = ModerateOrange,
            bgGradient = listOf(Color(0xFF2C5364), Color(0xFF0F2027))
        ),
        TrailCardItem(
            id = "kloof_corner",
            name = "Kloof Corner Ridge",
            location = "Table Mountain, WC",
            distance = "3.1 km",
            duration = "~1.5 h",
            elevation = "470 m",
            rating = "4.7",
            reviews = "(1.1k)",
            difficulty = "MODERATE",
            difficultyColor = ModerateOrange,
            bgGradient = listOf(Color(0xFF134E5E), Color(0xFF71B280))
        ),
        TrailCardItem(
            id = "swartberg_pass",
            name = "Swartberg Pass",
            location = "Oudtshoorn, Western Cape",
            distance = "12.4 km",
            duration = "~5 h",
            elevation = "1,230 m",
            rating = "4.9",
            reviews = "(856)",
            difficulty = "DIFFICULT",
            difficultyColor = DifficultRed,
            bgGradient = listOf(Color(0xFF3A6073), Color(0xFF3A7BD5))
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CreamBackground)
    ) {
        WhaleFallsTopAppBar()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 24.dp)
        ) {
            // Header
            Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Good morning, Explorer ",
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = DeepGreen
                    )
                    Icon(
                        imageVector = Icons.Outlined.Landscape,
                        contentDescription = null,
                        tint = DeepGreen,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Where will your next adventure take you?",
                    fontSize = 14.sp,
                    color = TextSecondary
                )
            }

            // Search Bar & Filter Button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Search trails, places or regions...", fontSize = 13.sp) },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = SageGreen)
                    },
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
                        modifier = Modifier
                            .padding(horizontal = 14.dp)
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.FilterList,
                            contentDescription = null,
                            tint = DeepGreen,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Filters", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = DeepGreen)
                    }
                }
            }

            // Category Filter Pills
            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    CategoryPill(
                        text = "All Trails",
                        iconDotColor = null,
                        isSelected = selectedCategory == "All Trails",
                        onClick = { selectedCategory = "All Trails" }
                    )
                }
                item {
                    CategoryPill(
                        text = "Easy",
                        iconDotColor = EasyGreen,
                        isSelected = selectedCategory == "Easy",
                        onClick = { selectedCategory = "Easy" }
                    )
                }
                item {
                    CategoryPill(
                        text = "Moderate",
                        iconDotColor = ModerateOrange,
                        isSelected = selectedCategory == "Moderate",
                        onClick = { selectedCategory = "Moderate" }
                    )
                }
                item {
                    CategoryPill(
                        text = "Difficult",
                        iconDotColor = DifficultRed,
                        isSelected = selectedCategory == "Difficult",
                        onClick = { selectedCategory = "Difficult" }
                    )
                }
                item {
                    CategoryPill(
                        text = "More",
                        hasDropdown = true,
                        isSelected = false,
                        onClick = { }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Recommended Section
            SectionHeader(title = "Recommended for you", onSeeAll = { })

            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(recommendedTrails) { trail ->
                    RecommendedTrailCard(
                        trail = trail,
                        onClick = { onTrailClick(trail.id) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Popular Regions Section
            SectionHeader(title = "Popular regions", onSeeAll = { })

            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item { RegionCard("Western Cape", "128 trails", Color(0xFF2E4057)) }
                item { RegionCard("KwaZulu-Natal", "96 trails", Color(0xFF048A81)) }
                item { RegionCard("Eastern Cape", "78 trails", Color(0xFF5A7D7C)) }
                item { RegionCard("Gauteng", "42 trails", Color(0xFF385E72)) }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Recently Viewed Section
            SectionHeader(title = "Recently viewed", onSeeAll = { })

            Column(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                RecentlyViewedCard(
                    name = "Devil's Peak",
                    location = "Cape Town, Western Cape",
                    distance = "7.0 km",
                    duration = "~3 h",
                    elevation = "1,009 m",
                    rating = "4.6",
                    reviews = "(732)",
                    isDownloaded = true,
                    onClick = { onTrailClick("devils_peak") }
                )
                RecentlyViewedCard(
                    name = "Sturrock Falls",
                    location = "Karkloof, KwaZulu-Natal",
                    distance = "4.2 km",
                    duration = "~1.5 h",
                    elevation = "300 m",
                    rating = "4.5",
                    reviews = "(512)",
                    isDownloaded = false,
                    onClick = { onTrailClick("sturrock_falls") }
                )
            }
        }
    }
}

@Composable
fun SectionHeader(
    title: String,
    onSeeAll: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = DeepGreen
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { onSeeAll() }
        ) {
            Text(
                text = "View all",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = DeepGreen
            )
            Spacer(modifier = Modifier.width(2.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = null,
                tint = DeepGreen,
                modifier = Modifier.size(12.dp)
            )
        }
    }
}

@Composable
fun CategoryPill(
    text: String,
    iconDotColor: Color? = null,
    hasDropdown: Boolean = false,
    isSelected: Boolean = false,
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
            if (iconDotColor != null) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(iconDotColor, shape = CircleShape)
                )
                Spacer(modifier = Modifier.width(6.dp))
            }
            Text(
                text = text,
                fontSize = 13.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = if (isSelected) Color.White else DeepGreen
            )
            if (hasDropdown) {
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = DeepGreen,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
fun RecommendedTrailCard(
    trail: TrailCardItem,
    onClick: () -> Unit
) {
    var isBookmarked by remember { mutableStateOf(false) }

    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardSurface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.width(220.dp)
    ) {
        Column {
            // Image Header Canvas with Badge and Bookmark
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .background(DeepGreen)
            ) {
                // Mountain Landscape canvas representation for photo
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawRect(color = trail.bgGradient.first())
                    val p = Path().apply {
                        moveTo(0f, size.height * 0.7f)
                        quadraticTo(size.width * 0.4f, size.height * 0.2f, size.width, size.height * 0.6f)
                        lineTo(size.width, size.height)
                        lineTo(0f, size.height)
                        close()
                    }
                    drawPath(p, trail.bgGradient.last())
                }

                // Difficulty Badge
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = trail.difficultyColor,
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.BottomStart)
                ) {
                    Text(
                        text = trail.difficulty,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }

                // Bookmark Icon
                Surface(
                    shape = CircleShape,
                    color = Color.Black.copy(alpha = 0.35f),
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.TopEnd)
                        .clickable { isBookmarked = !isBookmarked }
                ) {
                    Icon(
                        imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                        contentDescription = "Bookmark",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(6.dp)
                            .size(18.dp)
                    )
                }
            }

            // Card Body Text
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = trail.name,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = DeepGreen
                )
                Spacer(modifier = Modifier.height(2.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = null,
                        tint = SageGreen,
                        modifier = Modifier.size(12.dp)
                    )
                    Text(
                        text = trail.location,
                        fontSize = 11.sp,
                        color = TextSecondary,
                        maxLines = 1
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "${trail.distance}  •  ${trail.duration}  •  ${trail.elevation}", fontSize = 11.sp, color = TextPrimary)
                }

                Spacer(modifier = Modifier.height(6.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFFF39C12),
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = trail.rating, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = trail.reviews, fontSize = 11.sp, color = TextSecondary)
                }
            }
        }
    }
}

@Composable
fun RegionCard(
    name: String,
    trailCount: String,
    bgAccentColor: Color
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = bgAccentColor),
        modifier = Modifier
            .width(135.dp)
            .height(90.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Outlined.Park,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = Color.White
                )
                Text(
                    text = trailCount,
                    fontSize = 10.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }
    }
}

@Composable
fun RecentlyViewedCard(
    name: String,
    location: String,
    distance: String,
    duration: String,
    elevation: String,
    rating: String,
    reviews: String,
    isDownloaded: Boolean,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = CardSurface),
        border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Thumbnail image
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(DeepGreen)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawCircle(color = SageGreen, radius = size.width * 0.6f)
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = name,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = DeepGreen
                    )
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = DeepGreen.copy(alpha = 0.1f)
                    ) {
                        Text(
                            text = "SAVED",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = DeepGreen,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(2.dp))
                Text(text = location, fontSize = 11.sp, color = TextSecondary)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "$distance  •  $duration  •  $elevation", fontSize = 11.sp, color = TextPrimary)

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Color(0xFFF39C12),
                            modifier = Modifier.size(13.dp)
                        )
                        Text(text = " $rating $reviews", fontSize = 11.sp, color = TextSecondary)
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = if (isDownloaded) Icons.Default.CheckCircle else Icons.Outlined.FileDownload,
                            contentDescription = null,
                            tint = if (isDownloaded) EasyGreen else SageGreen,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = if (isDownloaded) "Downloaded" else "Not downloaded",
                            fontSize = 10.sp,
                            color = if (isDownloaded) EasyGreen else TextSecondary
                        )
                    }
                }
            }
        }
    }
}
