package lk.kotlin.android.example

import android.view.Gravity
import android.view.View
import lk.android.activity.access.ActivityAccess
import lk.android.extensions.selectableItemBackgroundResource
import lk.android.lifecycle.lifecycle
import lk.android.mighty.view.ViewGenerator
import lk.anko.adapters.observable.listAdapter
import lk.anko.extensions.anko
import lk.anko.extensions.verticalRecyclerView
import lk.kotlin.observable.property.StackObservableProperty
import lk.kotlin.observable.property.lifecycle.bind
import org.jetbrains.anko.*

/**
 * A [ViewGenerator] for selecting which demo you want to view.
 * Created by jivie on 5/5/16.
 */
class DemoGroupSelectorVG(val main: MainVG) : ViewGenerator {

    data class DemoGroup(val name: String, val demos: List<Demo>)
    data class Demo(val name: String, val maker: (StackObservableProperty<ViewGenerator>) -> ViewGenerator)

    companion object {
        val commonPackage = "lk.kotlin.android.example."
        val demos = listOf(
                lk.kotlin.android.example.activity.access.ActivityAccessVG::class.java,
                lk.kotlin.android.example.animations.HeightAnimatorVG::class.java,
                lk.kotlin.android.example.animations.TransitionViewVG::class.java,
                lk.kotlin.android.example.animations.SwapViewVG::class.java,
                lk.kotlin.android.example.animations.observable.ExpandingExampleVG::class.java,
                lk.kotlin.android.example.design.extensions.DesignExtensionsVG::class.java,
                lk.kotlin.android.example.dialogs.DialogsVG::class.java,
                lk.kotlin.android.example.extensions.SelectorVG::class.java,
                lk.kotlin.android.example.extensions.StickyHeadersVG::class.java,
                lk.kotlin.android.example.image.loading.ImageLoadingVG::class.java,
                lk.kotlin.android.example.image.loading.observable.ImageLoadingVG::class.java,
                lk.kotlin.android.example.image.loading.observable.LargeListImagesVG::class.java,
                lk.kotlin.android.example.observable.SimpleObservablePropertyVG::class.java,
                lk.kotlin.android.example.observable.MultipleBindingsVG::class.java,

                lk.kotlin.android.example.random.CoordinatorLayoutTestVG::class.java,
                lk.kotlin.android.example.random.ExampleLoginVG::class.java,
                lk.kotlin.android.example.random.NetImageTestVG::class.java,
                lk.kotlin.android.example.random.NetworkListVG::class.java,
                lk.kotlin.android.example.random.ObservableListVG::class.java,
                lk.kotlin.android.example.random.ObservableList2VG::class.java
        )
                .groupBy { it.name.substringBeforeLast('.') }
                .map {
                    DemoGroup(
                            name = it.key.removePrefix(commonPackage).split('.').joinToString(" ") { it.capitalize() },
                            demos = it.value.map { type ->
                                val name = type.simpleName.removeSuffix("VG").replace(Regex("[A-Z0-9]")) { " " + it.value }.trim()
                                Demo(name) { type.newInstance() }
                            }
                    )
                }
    }

    override fun invoke(access: ActivityAccess): View = access.context.anko().run {
        verticalLayout {

            textView(R.string.welcome_message) {
                minimumHeight = dip(48)
                padding = dip(16)
                gravity = Gravity.CENTER
            }

            verticalRecyclerView {
                adapter = listAdapter(demos) { itemObs ->
                    textView {
                        minimumHeight = dip(48)
                        padding = dip(16)
                        backgroundResource = selectableItemBackgroundResource
                        lifecycle.bind(itemObs) {
                            text = it.name
                        }
                        setOnClickListener { it: View? ->
                            main.stack.push(DemoSelectorVG(main, itemObs.value))
                        }
                    }.lparams(matchParent, wrapContent)
                }
            }
        }
    }
}

