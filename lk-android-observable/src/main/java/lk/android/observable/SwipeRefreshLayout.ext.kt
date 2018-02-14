@file:JvmName("LkAndroidObservable")
@file:JvmMultifileClass

package lk.android.observable



import android.support.v4.widget.SwipeRefreshLayout
import lk.android.lifecycle.lifecycle
import lk.kotlin.observable.property.MutableObservableProperty
import lk.kotlin.observable.property.StandardObservableProperty
import lk.kotlin.observable.property.lifecycle.bind

/**
 * Additional functionality for SwipeRefreshLayouts and observables.
 * Created by josep on 5/30/2017.
 */

inline fun SwipeRefreshLayout.onRefresh(
        progressObservable: MutableObservableProperty<Boolean> = StandardObservableProperty(false),
        crossinline action: (progressObservable: MutableObservableProperty<Boolean>) -> Unit
) {
    lifecycle.bind(progressObservable) {
        this.isRefreshing = it
    }
    setOnRefreshListener {
        action.invoke(progressObservable)
    }
}

inline fun SwipeRefreshLayout.onRefreshAndNow(
        progressObservable: MutableObservableProperty<Boolean> = StandardObservableProperty(false),
        crossinline action: (progressObservable: MutableObservableProperty<Boolean>) -> Unit
) {
    onRefresh(progressObservable, action)
    action.invoke(progressObservable)
}