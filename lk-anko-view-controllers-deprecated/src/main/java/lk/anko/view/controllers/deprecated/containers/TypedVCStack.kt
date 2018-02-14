package lk.anko.view.controllers.deprecated.containers


import lk.kotlin.observable.property.StackObservableProperty

@Deprecated("Use observable properties directly", ReplaceWith("StackObservableProperty<T>", "com.lightningkite.kotlin.observable.property.StackObservableProperty"))
typealias TypedVCStack<T> = StackObservableProperty<T>
