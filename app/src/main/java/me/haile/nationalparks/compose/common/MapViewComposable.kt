package me.haile.nationalparks.compose.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

@Composable
fun MapViewComposable(lat: Double, lng: Double) {
    val context = LocalContext.current
    var mapView: MapView? = null
    var googleMap: GoogleMap? by remember { mutableStateOf(null) }

    val latLng = LatLng(lat, lng)

    AndroidView(
        { ctx ->
            MapView(ctx).apply {
                mapView = this
                onCreate(null)
                getMapAsync {
                    googleMap = it
                    it.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
                    it.addMarker(MarkerOptions().position(latLng))
                }
            }
        }, modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 200.dp) // Set max height to 300dp
    ) { myMapView ->
        myMapView.onStart()
        myMapView.onResume()
        myMapView.getMapAsync { map ->
            googleMap = map
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
            map.addMarker(MarkerOptions().position(latLng))
        }
    }

    DisposableEffect(mapView) {
        onDispose {
            mapView?.onPause()
            mapView?.onStop()
            mapView?.onDestroy()
        }
    }
}