package lk.anko.view.controllers.deprecated.containers


import lk.anko.view.controllers.deprecated.ViewController
import lk.kotlin.observable.property.StandardObservableProperty

@Deprecated("Use observable properties directly", ReplaceWith("StandardObservableProperty<Any>", "com.lightningkite.kotlin.observable.property.StandardObservableProperty"))
typealias VCSwapper = StandardObservableProperty<Any>

@Deprecated("Just change the value.", ReplaceWith("this.value = other"))
fun VCSwapper.swap(other: ViewController) {
    this.value = other
}