package com.example.whalefalls_casus.ui.map

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.compose.ui.zIndex
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.whalefalls_casus.theme.TextMuted
import com.example.whalefalls_casus.theme.TextPrimary
import com.example.whalefalls_casus.theme.TextSecondary
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.Alignment
import org.maplibre.android.camera.CameraUpdateFactory
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.MapLibreMap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts

@Composable
fun TrailMapScreen(
    trailId: String = "lions_head",
    onBackClick: () -> Unit
) {
    val context = LocalContext.current

    val fusedLocationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    var currentLocation by remember {
        mutableStateOf<Location?>(null)
    }

    var hasLocationPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
        )
    }
    var mapLibreMap by remember {
        mutableStateOf<MapLibreMap?>(null)
    }

    fun getCurrentLocation() {
        try {
            fusedLocationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                CancellationTokenSource().token
            ).addOnSuccessListener { location ->

                if (location != null) {
                    currentLocation = location

                    mapLibreMap?.animateCamera(
                        CameraUpdateFactory.newLatLngZoom(
                            LatLng(
                                location.latitude,
                                location.longitude
                            ),
                            16.0
                        )
                    )
                }
            }
        } catch (e: SecurityException) {
            hasLocationPermission = false
        }
    }
    val locationPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->

            val granted =
                permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                        permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true

            hasLocationPermission = granted

            if (granted) {
                getCurrentLocation()
            }
        }
    currentLocation?.let { location ->
        Text(
            text = "GPS: ${location.latitude}, ${location.longitude}",
            color = TextPrimary,
            fontSize = 12.sp
        )
    }
    var isTracking by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFC7E3D0))
    ) {
        MapLibreMapView(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(0f),
            trailId = trailId,
            onMapReady = { map ->
                mapLibreMap = map
            }
        )
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(
                    top = 20.dp,
                    end = 20.dp
                )
                .zIndex(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    mapLibreMap?.animateCamera(
                        CameraUpdateFactory.zoomIn()
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.DarkGray
                )

            ) {
                Text("Zoom in")
            }

            Button(
                onClick = {
                    mapLibreMap?.animateCamera(
                        CameraUpdateFactory.zoomOut()
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.DarkGray
                )
            ) {
                Text("Zoom out")
            }

            Button(
                onClick = {
                    mapLibreMap?.animateCamera(
                        CameraUpdateFactory.newLatLngZoom(
                            LatLng(-33.9368, 18.3947),
                            14.0
                        )
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.DarkGray
                )
            ) {
                Text("Re-center")
            }
            Text(
                text = "Map data © OpenStreetMap contributors",
                fontSize = 10.sp,
                color = Color.DarkGray
            )
            Button(
                onClick = {
                    if (hasLocationPermission) {
                        getCurrentLocation()
                    } else {
                        locationPermissionLauncher.launch(
                            arrayOf(
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            )
                        )
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.DarkGray
                )
            ) {
                Text("My location")
            }

        @Composable
        fun TrackingStat(
            label: String,
            mainVal: String,
            subVal: String
        ) {
            Column {
                Text(
                    text = label,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextSecondary
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = mainVal,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                Text(text = subVal, fontSize = 10.sp, color = TextMuted)
            }
        }
    }
}
}