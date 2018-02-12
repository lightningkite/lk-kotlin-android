package com.lightningkite.kotlin.anko.viewcontrollers.containers

import com.lightningkite.kotlin.anko.activity.ViewGenerator
import com.lightningkite.kotlin.anko.viewcontrollers.ViewController
import com.lightningkite.kotlin.observable.property.ObservableProperty

        /**
         * Something that contains [ViewController]s and handles the changes between them.
         * Created by jivie on 10/12/15.
         */
@Deprecated("Use observable properties directly", ReplaceWith(
        "ObservableProperty<ViewGenerator>",
        "com.lightningkite.kotlin.observable.property.ObservableProperty",
        "com.lightningkite.kotlin.anko.activity.ViewGenerator"
))
typealias VCContainer = ObservableProperty<ViewGenerator>