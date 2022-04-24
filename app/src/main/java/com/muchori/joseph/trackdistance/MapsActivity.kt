package com.muchori.joseph.trackdistance

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.muchori.joseph.trackdistance.databinding.ActivityMapsBinding
import com.muchori.joseph.trackdistance.misc.CameraAndViewport
import com.muchori.joseph.trackdistance.misc.TypeAndStyle

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

  private lateinit var map: GoogleMap
  private lateinit var binding: ActivityMapsBinding

  private val typeAndStyle by lazy { TypeAndStyle() }
  private val cameraAndViewport by lazy { CameraAndViewport() }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMapsBinding.inflate(layoutInflater)
    setContentView(binding.root)

    // Obtain the SupportMapFragment and get notified when the map is ready to be used.
    val mapFragment = supportFragmentManager
      .findFragmentById(R.id.map) as SupportMapFragment
    mapFragment.getMapAsync(this)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.maps_menu_types, menu)

    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    typeAndStyle.setMapType(item, map)
    return true
  }

  override fun onMapReady(googleMap: GoogleMap) {
    map = googleMap

    // Add a marker in Meru and move the camera to 10f
    val nairobi = LatLng(-1.2984800775976113, 36.81342312166572)
    map.addMarker(MarkerOptions().position(nairobi).title("Nairobi"))
    //map.moveCamera(CameraUpdateFactory.newLatLngZoom(meru, 10f))
    map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewport.nairobi))
    map.uiSettings.apply {
      isZoomControlsEnabled = true
    }
    typeAndStyle.setMapStyle(map, this)
//    map.setMinZoomPreference(15f)
//    map.setMaxZoomPreference(17f)

//    lifecycleScope.launch {
//      delay(4000L)
//      map.moveCamera(CameraUpdateFactory.zoomBy(3f))
//    }

    onMapClicked()
    onMpaLongClicked()
//    lifecycleScope.launch {
//      delay(4000L)
//      map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewport.nairobi), 2000, null)
//      map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewport.nairobi), 2000, object : GoogleMap.CancelableCallback {
//        override fun onCancel() {
//          Toast.makeText(this@MapsActivity, "Canceled", Toast.LENGTH_SHORT).show()
//        }
//
//        override fun onFinish() {
//          Toast.makeText(this@MapsActivity, "Finished", Toast.LENGTH_SHORT).show()
//        }
//      })
//      //map.animateCamera(CameraUpdateFactory.newLatLngBounds(cameraAndViewport.nairobiBounds, 0), 2000, null)
//      //map.setLatLngBoundsForCameraTarget(cameraAndViewport.nairobiBounds) // setting bounds to a location
//    }
  }

  private fun onMapClicked() {
    map.setOnMapClickListener {
      Toast.makeText(this, "Single Click", Toast.LENGTH_SHORT).show()
    }
  }

  private fun onMpaLongClicked() {
    map.setOnMapLongClickListener {
      map.addMarker(MarkerOptions().position(it).title("New Marker"))
      Toast.makeText(this, "Long Clicked", Toast.LENGTH_SHORT).show()
    }
  }
}