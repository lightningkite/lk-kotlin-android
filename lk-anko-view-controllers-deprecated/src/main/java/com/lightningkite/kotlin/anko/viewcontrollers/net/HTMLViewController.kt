package com.lightningkite.kotlin.anko.viewcontrollers.net

import android.view.View
import com.lightningkite.kotlin.anko.viewcontrollers.AnkoViewController
import com.lightningkite.kotlin.anko.viewcontrollers.VCContext
import org.jetbrains.anko.*

/**
 * Simply displays HTML in a view controller.  Most useful for displaying networking error messages.
 *
 * Created by joseph on 8/4/16.
 */
@Deprecated("Deprecated along with ViewControllers in general.")
class HTMLViewController(val title: String?, val html: String) : AnkoViewController() {
    override fun createView(ui: AnkoContext<VCContext>): View = ui.verticalLayout {
        if (title != null) {
            textView(text = title) {
                padding = dip(8)
            }.lparams(matchParent, wrapContent)
        }
        webView {
            println("html= $html")
            loadData(html, "text/html", "UTF-8")
        }.lparams(matchParent, matchParent)
    }
}