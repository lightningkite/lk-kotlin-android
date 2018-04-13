@file:JvmName("LkAndroidExtensions")
@file:JvmMultifileClass

package lk.android.extensions



import android.annotation.SuppressLint
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper

/**
 * Gets a single location update, returning the current location when found through a callback.
 *
 * This function does assume we have permission already.
 */
@SuppressLint("MissingPermission")
inline fun LocationManager.requestSingleUpdate(
        criteria: Criteria = Criteria(),
        crossinline onLocationHad: (Location) -> Unit
) {
    requestSingleUpdate(criteria, object : LocationListener {
        override fun onLocationChanged(location: Location) {
            onLocationHad(location)
        }

        override fun onProviderDisabled(p0: String?) {
        }

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
        }

        override fun onProviderEnabled(p0: String?) {
        }
    }, Looper.getMainLooper())
}