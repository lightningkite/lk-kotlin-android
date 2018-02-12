package com.lightningkite.kotlin.anko.viewcontrollers

import android.view.View
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContextImpl

/**
 * A [CallbackViewController] where the view is created using Anko.
 * In the future, this should allow the Anko preview to work with your code.
 *
 * Created by jivie on 1/19/16.
 */

@Deprecated("Deprecated along with ViewControllers in general.")
abstract class AnkoViewController() : CallbackViewController(), AnkoComponent<VCContext> {
    override final fun makeView(vcContext: VCContext): View {
        return createView(AnkoContextImpl(vcContext.context, vcContext, false))
    }
}
