package com.lightningkite.kotlin.anko.activity

import android.view.View
import com.lightningkite.kotlin.anko.anko
import org.jetbrains.anko.AnkoContextImpl

typealias ViewGenerator = (ActivityAccess) -> View

inline fun ActivityAccess.anko(setup: AnkoContextImpl<ActivityAccess>.() -> Unit) = context.anko(this, setup)