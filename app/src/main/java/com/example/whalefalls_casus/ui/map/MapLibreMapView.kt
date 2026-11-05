
package com.example.whalefalls_casus.ui.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import org.maplibre.android.MapLibre
import org.maplibre.android.camera.CameraPosition
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.MapLibreMap
import org.maplibre.android.maps.MapView
import org.maplibre.android.style.layers.LineLayer
import org.maplibre.android.style.layers.PropertyFactory
import org.maplibre.android.style.sources.GeoJsonSource

private const val MAP_STYLE =
    "https://tiles.openfreemap.org/styles/liberty"

private const val ROUTE_SOURCE = "lions-head-route"
private const val ROUTE_LAYER = "lions-head-route-line"

private const val ROUTE_GEOJSON = """
{
  "type": "Feature",
  "geometry": {
    "type": "LineString",
    "coordinates": [
      [18.39443, -33.92981],
      [18.39387, -33.92951],
      [18.39285, -33.93094],
      [18.39212, -33.93116],
      [18.39120, -33.93117],
      [18.38955, -33.93145],
      [18.38865, -33.93170],
      [18.38779, -33.93225],
      [18.38878, -33.93222],
      [18.38975, -33.93239],
      [18.38849, -33.93314],
      [18.38703, -33.93369],
      [18.38659, -33.93399],
      [18.38661, -33.93419],
      [18.38716, -33.93449],
      [18.38707, -33.93402],
      [18.38785, -33.93374],
      [18.38995, -33.93391],
      [18.39061, -33.93508],
      [18.39058, -33.93568],
      [18.38979, -33.93645],
      [18.38944, -33.93572]
    ]
  },
  "properties": {}
}
"""

@Composable
fun MapLibreMapView(
    modifier: Modifier = Modifier,
    trailId: String = "lions_head",
    onMapReady: (MapLibreMap) -> Unit = {}
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val currentOnMapReady = rememberUpdatedState(onMapReady)


    val mapView = remember {
        MapLibre.getInstance(context.applicationContext)
        MapView(context).apply {
            onCreate(null)
        }
    }

    DisposableEffect(mapView, lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> mapView.onStart()
                Lifecycle.Event.ON_RESUME -> mapView.onResume()
                Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                Lifecycle.Event.ON_STOP -> mapView.onStop()
                else -> Unit
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            mapView.onDestroy()
        }
    }

    AndroidView(
        modifier = modifier,
        factory = { mapView },
        update = { view ->
            view.getMapAsync { map ->
                if (map.style == null) {
                    map.setStyle(MAP_STYLE) { style ->

                        style.addSource(
                            GeoJsonSource(
                                ROUTE_SOURCE,
                                when (trailId) {
                                    "kloof_corner" -> TrailRouteData.KLOOF_CORNER
                                    "swartberg_pass" -> TrailRouteData.SWARTBERG_PASS
                                    else -> ROUTE_GEOJSON
                                }
                            )
                        )

                        style.addLayer(
                            LineLayer(
                                ROUTE_LAYER,
                                ROUTE_SOURCE
                            ).withProperties(
                                PropertyFactory.lineColor("#E65100"),
                                PropertyFactory.lineWidth(4f)
                            )
                        )

                        map.cameraPosition =
                            CameraPosition.Builder()
                                .target(LatLng(-33.933, 18.3905))
                                .zoom(14.0)
                                .build()

                        currentOnMapReady.value(map)
                    }
                } else {
                    currentOnMapReady.value(map)
                }
            }
        }
    )
}
