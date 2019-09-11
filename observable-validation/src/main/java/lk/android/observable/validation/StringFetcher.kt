@file:JvmName("LkAndroidObservableValidation")
@file:JvmMultifileClass

package lk.android.observable.validation



import android.content.res.Resources
import androidx.annotation.StringRes

/**
 * Abstraction for handling string fetching, as for unit tests we don't have access to [Resources].
 * Created by joseph on 11/2/17.
 */
typealias StringFetcher = (Resources) -> String

/**
 * Directly holds an returns a string.
 */
class StringFetcherDirect(val data: String) : StringFetcher {
    override fun invoke(resources: Resources): String = data
}

/**
 * Retrieves a string from resources.
 */
class StringFetcherResource(@StringRes val resource: Int) : StringFetcher {
    override fun invoke(resources: Resources): String = resources.getString(resource)
}