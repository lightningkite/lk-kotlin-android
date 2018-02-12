package com.lightningkite.kotlin.anko.viewcontrollers.containers

import com.lightningkite.kotlin.anko.activity.ViewGenerator
import com.lightningkite.kotlin.anko.viewcontrollers.ViewController
import com.lightningkite.kotlin.observable.property.StackObservableProperty

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
