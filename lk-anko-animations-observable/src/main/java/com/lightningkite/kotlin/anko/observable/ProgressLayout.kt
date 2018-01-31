@file:JvmName("LkAnkoAnimationsObservable")
@file:JvmMultifileClass

package com.lightningkite.kotlin.anko.observable

import android.view.Gravity
import android.view.View
import android.view.ViewManager
import android.widget.FrameLayout
import com.lightningkite.kotlin.anko.animation.TransitionView
import com.lightningkite.kotlin.anko.animation._TransitionView
import com.lightningkite.kotlin.anko.animation.transitionView
import com.lightningkite.kotlin.anko.lifecycle
import com.lightningkite.kotlin.observable.property.MutableObservableProperty
import com.lightningkite.kotlin.observable.property.ObservableProperty
import com.lightningkite.kotlin.observable.property.StandardObservableProperty
import com.lightningkite.kotlin.observable.property.bind
import org.jetbrains.anko.margin
import org.jetbrains.anko.padding
import org.jetbrains.anko.progressBar
import org.jetbrains.anko.wrapContent

/**
 * Wraps another view in a [TransitionView] that transitions between a [ProgressBar] and the given
 * view whenever the [runningObs] changes.
 *
 * Created by jivie on 7/15/16.
 */
fun ViewManager.progressLayout(
        runningObs: MutableObservableProperty<Boolean> = StandardObservableProperty(false),
        progressSize: Int = wrapContent,
        otherViewMaker: _TransitionView.(running: MutableObservableProperty<Boolean>) -> View
): _TransitionView {
    return transitionView() {
        otherViewMaker(runningObs).tag("content").apply {
            (layoutParams as FrameLayout.LayoutParams).gravity = Gravity.CENTER
        }
        progressBar().lparams(progressSize, progressSize) { margin = 0 }.tag("loading").apply {
            minimumWidth = 0
            minimumHeight = 0
            padding = 0
            (layoutParams as FrameLayout.LayoutParams).gravity = Gravity.CENTER
        }

        lifecycle.bind(runningObs) { loading ->
            if (loading) animate("loading")
            else animate("content")
        }
    }
}

/**
 * Wraps another view in a [TransitionView] that transitions between a [ProgressBar] and the given
 * view whenever the [runningObs] changes.
 *
 * Created by jivie on 7/15/16.
 */
fun ViewManager.progressLayoutReadOnly(
        runningObs: ObservableProperty<Boolean>,
        otherViewMaker: _TransitionView.() -> View
): _TransitionView {
    return transitionView() {
        otherViewMaker().tag("content").apply {
            (layoutParams as FrameLayout.LayoutParams).gravity = Gravity.CENTER
        }
        progressBar().lparams(wrapContent, wrapContent).tag("loading").apply {
            minimumWidth = 0
            minimumHeight = 0
            (layoutParams as FrameLayout.LayoutParams).gravity = Gravity.CENTER
        }

        lifecycle.bind(runningObs) { loading ->
            if (loading) animate("loading")
            else animate("content")
        }
    }
}