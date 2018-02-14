package lk.everything.deprecated.networking

import lk.android.ui.thread.UIThread
import lk.kotlin.okhttp.TypedResponse
import lk.kotlin.utils.lambda.invokeAll
import java.util.*

/**
 * Used for capturing errors
 * Created by joseph on 11/11/16.
 */
@Deprecated("Just do it yourself.")
interface ErrorCaptureApi {

    val onError: ArrayList<(TypedResponse<*>) -> Unit>

    fun <T> (() -> TypedResponse<T>).captureError(): () -> TypedResponse<T> {
        return {
            val response = this.invoke()
            if (!response.isSuccessful()) {
                UIThread.execute {
                    onError.invokeAll(response)
                }
            }
            response
        }
    }
}