@file:JvmName("LkAnkoActivityAccess")
@file:JvmMultifileClass

package lk.anko.activity.access

import lk.android.activity.access.ActivityAccess
import lk.anko.extensions.anko
import org.jetbrains.anko.AnkoContextImpl

inline fun ActivityAccess.anko(setup: AnkoContextImpl<ActivityAccess>.() -> Unit) = context.anko(this, setup)