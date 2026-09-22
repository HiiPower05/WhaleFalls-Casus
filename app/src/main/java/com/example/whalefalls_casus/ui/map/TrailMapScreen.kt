package com.example.whalefalls_casus.ui.map

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material.icons.outlined.Layers
import androidx.compose.material.icons.outlined.NearMe
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whalefalls_casus.theme.CardSurface
import com.example.whalefalls_casus.theme.CreamBackground
import com.example.whalefalls_casus.theme.DeepGreen
import com.example.whalefalls_casus.theme.EasyGreen
import com.example.whalefalls_casus.theme.ModerateOrange
import com.example.whalefalls_casus.theme.SageGreen
import com.example.whalefalls_casus.theme.SurfaceBorder
import com.example.whalefalls_casus.theme.TextPrimary
import com.example.whalefalls_casus.theme.TextSecondary
import com.example.whalefalls_casus.theme.TextMuted
import com.example.whalefalls_casus.ui.components.WhaleFallsLogo

@Composable
fun TrailMapScreen(
    trailId: String = "lions_head",
    onBackClick: () -> Unit
) {
    var isTracking by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFC7E3D0))
    ) {
        // Map Canvas Drawing
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // Water / Ocean area (left side)
            val oceanPath = Path().apply {
                moveTo(0f, 0f)
                lineTo(w * 0.35f, 0f)
                quadraticTo(w * 0.25f, h * 0.4f, w * 0.45f, h * 0.7f)
                quadraticTo(w * 0.50f, h * 0.85f, w * 0.30f, h)
                lineTo(0f, h)
                close()
            }
            drawPath(oceanPath, Color(0xFF90C2E7))

            // Contour lines on land
            for (i in 1..8) {
                val contourPath = Path().apply {
                    moveTo(w * 0.3f, h * (i * 0.12f))
                    quadraticTo(w * 0.65f, h * (i * 0.1f), w, h * (i * 0.13f))
                }
                drawPath(contourPath, Color(0xFFB5D8BF), style = Stroke(width = 1.5f))
            }

            // Trail Polyline Route
            val routePath = Path().apply {
                moveTo(w * 0.62f, h * 0.60f) // Start: Sea Point
                lineTo(w * 0.50f, h * 0.48f) // 2 km
                lineTo(w * 0.45f, h * 0.38f) // 4 km
                lineTo(w * 0.35f, h * 0.26f) // Lion's Head summit
            }
            drawPath(routePath, DeepGreen, style = Stroke(width = 5f))

            // Start Dot
            drawCircle(color = EasyGreen, radius = 10f, center = Offset(w * 0.62f, h * 0.60f))
            drawCircle(color = Color.White, radius = 5f, center = Offset(w * 0.62f, h * 0.60f))

            // Summit Marker
            drawCircle(color = DeepGreen, radius = 12f, center = Offset(w * 0.35f, h * 0.26f))
        }

        // Top Header Overlay
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, start = 16.dp, end = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = CircleShape,
                    color = CreamBackground,
                    modifier = Modifier.size(40.dp)
                ) {
                    IconButton(onClick = onBackClick) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = DeepGreen)
                    }
                }

                WhaleFallsLogo(whaleHeight = 32.dp, compactText = true)

                Row {
                    Surface(shape = CircleShape, color = CreamBackground, modifier = Modifier.size(40.dp)) {
                        IconButton(onClick = { }) {
                            Icon(imageVector = Icons.Outlined.Layers, contentDescription = "Layers", tint = DeepGreen)
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Surface(shape = CircleShape, color = CreamBackground, modifier = Modifier.size(40.dp)) {
                        IconButton(onClick = { }) {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More", tint = DeepGreen)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Floating Trail Stats Card
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = CardSurface),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "Lion's Head", fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = DeepGreen)
                            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null, tint = DeepGreen)
                        }
                        Surface(shape = RoundedCornerShape(10.dp), color = ModerateOrange) {
                            Text(text = "MODERATE", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.White, modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(text = "🛣️ 5.5 km", fontSize = 12.sp, color = TextPrimary)
                        Text(text = "⏱️ ~2 h", fontSize = 12.sp, color = TextPrimary)
                        Text(text = "⛰️ 669 m", fontSize = 12.sp, color = TextPrimary)
                    }
                }
            }
        }

        // Re-center Button (Bottom Left floating)
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = CardSurface,
            shadowElevation = 4.dp,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 16.dp, bottom = 240.dp)
                .clickable { }
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Outlined.NearMe, contentDescription = null, tint = DeepGreen, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "Re-center", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = DeepGreen)
            }
        }

        // Map Controls (Right floating)
        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 240.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Surface(shape = CircleShape, color = DeepGreen, shadowElevation = 4.dp, modifier = Modifier.size(38.dp)) {
                Box(contentAlignment = Alignment.Center) {
                    Text(text = "N", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
            }

            Surface(shape = RoundedCornerShape(12.dp), color = CardSurface, shadowElevation = 4.dp) {
                Column {
                    IconButton(onClick = { }, modifier = Modifier.size(38.dp)) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Zoom In", tint = DeepGreen)
                    }
                    IconButton(onClick = { }, modifier = Modifier.size(38.dp)) {
                        Icon(imageVector = Icons.Default.Remove, contentDescription = "Zoom Out", tint = DeepGreen)
                    }
                }
            }
        }

        // Bottom Tracking Sheet Card
        Surface(
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            color = CreamBackground,
            shadowElevation = 16.dp,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 14.dp)
            ) {
                // Drag Handle
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(4.dp)
                        .background(SageGreen, shape = RoundedCornerShape(2.dp))
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 4 Live Tracking Stats
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TrackingStat(label = "DISTANCE", mainVal = "0.0 km", subVal = "of 5.5 km")
                    TrackingStat(label = "TIME ELAPSED", mainVal = "00:00", subVal = "of ~2 h")
                    TrackingStat(label = "ELEVATION GAIN", mainVal = "0 m", subVal = "of 669 m")
                    TrackingStat(label = "AVG PACE", mainVal = "--", subVal = "min/km")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Progress Bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.size(10.dp).background(EasyGreen, shape = CircleShape))
                    Spacer(modifier = Modifier.width(6.dp))
                    LinearProgressIndicator(
                        progress = { 0.05f },
                        modifier = Modifier.weight(1f).height(6.dp),
                        color = EasyGreen,
                        trackColor = SurfaceBorder
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(imageVector = Icons.Default.Flag, contentDescription = null, tint = DeepGreen, modifier = Modifier.size(16.dp))
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = { },
                        shape = RoundedCornerShape(12.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder),
                        modifier = Modifier.weight(1f).height(50.dp)
                    ) {
                        Icon(imageVector = Icons.Outlined.FileDownload, contentDescription = null, tint = DeepGreen)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "Download map", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DeepGreen)
                    }

                    Button(
                        onClick = { isTracking = !isTracking },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = DeepGreen, contentColor = Color.White),
                        modifier = Modifier.weight(1.2f).height(50.dp)
                    ) {
                        Icon(
                            imageVector = if (isTracking) Icons.Default.PauseCircle else Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (isTracking) "Pause Tracking" else "Start Tracking",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TrackingStat(
    label: String,
    mainVal: String,
    subVal: String
) {
    Column {
        Text(text = label, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = TextSecondary)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = mainVal, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
        Text(text = subVal, fontSize = 10.sp, color = TextMuted)
    }
}
