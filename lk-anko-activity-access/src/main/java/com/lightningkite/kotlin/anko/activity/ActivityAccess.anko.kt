@file:JvmName("LkAnkoActivityAccess")
@file:JvmMultifileClass

package com.lightningkite.kotlin.anko.activity

import com.lightningkite.kotlin.anko.anko
import org.jetbrains.anko.AnkoContextImpl

inline fun ActivityAccess.anko(setup: AnkoContextImpl<ActivityAccess>.() -> Unit) = context.anko(this, setup)