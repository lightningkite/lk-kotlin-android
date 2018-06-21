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
import lk.kotlin.android.example.random.ObservableList2VG
import lk.kotlin.observable.property.StackObservableProperty
import lk.kotlin.observable.property.lifecycle.bind
import org.jetbrains.anko.*
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

/**
 * A [ViewGenerator] for selecting which demo you want to view.
 * Created by jivie on 5/5/16.
 */
class DemoGroupSelectorVG(val stack: StackObservableProperty<ViewGenerator>) : ViewGenerator {

    data class DemoGroup(val name: String, val demos: List<Demo>)
    data class Demo(
            val name: String,
            val groupName: String,
            val kClass: KClass<*>,
            val maker: (StackObservableProperty<ViewGenerator>) -> ViewGenerator
    )

    companion object {
        val commonPackage = "lk.kotlin.android.example."
        fun KClass<*>.default(
                maker: (StackObservableProperty<ViewGenerator>) -> ViewGenerator = {
                    this.createInstance() as ViewGenerator
                }
        ) = Demo(
                name = simpleName!!.removeSuffix("VG").replace(Regex("[A-Z0-9]")) { " " + it.value }.trim(),
                kClass = this,
                groupName = this.qualifiedName!!.substringBeforeLast('.').removePrefix(commonPackage).split('.').joinToString(" ") { it.capitalize() },
                maker = maker
        )

        val demos = listOf<Demo>(
                lk.kotlin.android.example.activity.access.ActivityAccessVG::class.default(),
                lk.kotlin.android.example.animations.HeightAnimatorVG::class.default(),
                lk.kotlin.android.example.animations.TransitionViewVG::class.default(),
                lk.kotlin.android.example.animations.SwapViewVG::class.default(),
                lk.kotlin.android.example.animations.observable.ExpandingExampleVG::class.default(),
                lk.kotlin.android.example.design.extensions.DesignExtensionsVG::class.default(),
                lk.kotlin.android.example.dialogs.DialogsVG::class.default(),
                lk.kotlin.android.example.extensions.SelectorVG::class.default(),
                lk.kotlin.android.example.extensions.StickyHeadersVG::class.default(),
                lk.kotlin.android.example.image.loading.ImageLoadingVG::class.default(),
                lk.kotlin.android.example.image.loading.observable.ImageLoadingVG::class.default(),
                lk.kotlin.android.example.image.loading.observable.LargeListImagesVG::class.default(),
                lk.kotlin.android.example.observable.SimpleObservablePropertyVG::class.default(),
                lk.kotlin.android.example.observable.MultipleBindingsVG::class.default(),

                lk.kotlin.android.example.random.CoordinatorLayoutTestVG::class.default(),
                lk.kotlin.android.example.random.ExampleLoginVG::class.default(),
                lk.kotlin.android.example.random.NetImageTestVG::class.default(),
                lk.kotlin.android.example.random.NetworkListVG::class.default(),
                lk.kotlin.android.example.random.ObservableListVG::class.default(),
                lk.kotlin.android.example.random.ObservableList2VG::class.default { ObservableList2VG(it) }
        )
                .groupBy { it.groupName }
                .map {
                    DemoGroup(
                            name = it.key,
                            demos = it.value
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
                            stack.push(DemoSelectorVG(stack, itemObs.value))
                        }
                    }.lparams(matchParent, wrapContent)
                }
            }
        }
    }
}

