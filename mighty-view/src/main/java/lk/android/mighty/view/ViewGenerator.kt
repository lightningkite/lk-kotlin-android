package lk.android.mighty.view


import android.view.View
import lk.android.activity.access.ActivityAccess

/**
 * Something that generates a [View] from an [ActivityAccess].
 * Typically in instance of this represents a particular screen in an app.
 */
typealias ViewGenerator = (ActivityAccess) -> View