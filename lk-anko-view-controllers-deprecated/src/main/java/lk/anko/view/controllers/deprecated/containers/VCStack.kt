package lk.anko.view.controllers.deprecated.containers


import lk.android.activity.access.ViewGenerator
import lk.anko.view.controllers.deprecated.ViewController
import lk.kotlin.observable.property.StackObservableProperty

        /**
         * A stack of [ViewController]s.  You can [push] and [pop] them, among other things.
         * Any controllers popped off the stack are disposed.
         *
         * Created by jivie on 10/12/15.
         */
@Deprecated("Use observable properties directly", ReplaceWith(
        "StackObservableProperty<ViewGenerator>",
        "com.lightningkite.kotlin.observable.property.StackObservableProperty",
        "com.lightningkite.kotlin.anko.activity.ViewGenerator"
))
typealias VCStack = StackObservableProperty<ViewGenerator>
