package com.muchori.joseph.trackdistance.misc

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

class CameraAndViewport {

  val nairobi: CameraPosition = CameraPosition.Builder()
    .target(LatLng(-1.2984800775976113, 36.81342312166572))
    .zoom(17f)
    .bearing(100f)
    .tilt(45f)
    .build()

  //setting bound in a location
  val nairobiBounds = LatLngBounds(
    LatLng(-1.3545731305839022, 36.89909668564966),
    LatLng(-1.2984800775976113, 36.81342312166572)
  )
}