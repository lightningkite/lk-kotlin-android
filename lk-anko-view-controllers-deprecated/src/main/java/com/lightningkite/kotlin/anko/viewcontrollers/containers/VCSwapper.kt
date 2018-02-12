package com.lightningkite.kotlin.anko.viewcontrollers.containers

import com.lightningkite.kotlin.anko.viewcontrollers.ViewController
import com.lightningkite.kotlin.observable.property.StandardObservableProperty

@Deprecated("Use observable properties directly", ReplaceWith("StandardObservableProperty<Any>", "com.lightningkite.kotlin.observable.property.StandardObservableProperty"))
typealias VCSwapper = StandardObservableProperty<Any>

@Deprecated("Just change the value.", ReplaceWith("this.value = other"))
fun VCSwapper.swap(other: ViewController) {
    this.value = other
}