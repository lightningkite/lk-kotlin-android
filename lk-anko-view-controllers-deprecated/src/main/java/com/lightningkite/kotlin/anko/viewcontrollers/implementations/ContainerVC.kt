package com.lightningkite.kotlin.anko.viewcontrollers.implementations

import android.content.res.Resources
import android.view.View
import android.widget.FrameLayout
import com.lightningkite.kotlin.anko.viewcontrollers.VCContext
import com.lightningkite.kotlin.anko.viewcontrollers.ViewController
import com.lightningkite.kotlin.anko.viewcontrollers.containers.VCContainer
import com.lightningkite.kotlin.observable.property.StackObservableProperty
import org.jetbrains.anko._FrameLayout
import org.jetbrains.anko.matchParent
import java.util.*

/**
 * Contains a given [VCContainer], embedding the container of views inside this view controller.
 * Useful if you want to have a smaller section of your view that changes, like you might with tabs.
 * Created by jivie on 10/14/15.
 */
@Deprecated("Deprecated along with ViewControllers in general.")
open class ContainerVC(
        val container: VCContainer,
        val disposeContainer: Boolean = true,
        val layoutParams: () -> FrameLayout.LayoutParams = { FrameLayout.LayoutParams(matchParent, matchParent) }
) : ViewController {

    private var embedders = HashMap<View, VCContainerEmbedder>()

    override fun make(vcContext: VCContext): View {
        val vcView = _FrameLayout(vcContext.context).apply {
            embedders[this] = (VCContainerEmbedder(vcContext, this, container, { layoutParams() }))
        }
        return vcView
    }

    override fun unmake(view: View) {
        embedders[view]?.unmake()
        embedders.remove(view)
        super.unmake(view)
    }

    override fun onBackPressed(backAction: () -> Unit) {
        val top = container.value
        val default = {
            if (container is StackObservableProperty<*> && container.stack.size > 1) {
                container.pop()
            } else backAction.invoke()
        }
        if (top is ViewController)
            top.onBackPressed(default)
        else default.invoke()
    }

    override fun getTitle(resources: Resources): String {
        val top = container.value
        return if (top is ViewController)
            top.getTitle(resources)
        else ""
    }
}