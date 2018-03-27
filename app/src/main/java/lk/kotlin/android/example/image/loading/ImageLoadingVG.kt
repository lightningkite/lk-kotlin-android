package lk.kotlin.android.example.image.loading

import android.view.View
import lk.android.activity.access.ActivityAccess
import lk.android.image.loading.image.lambdaBitmap
import lk.android.mighty.view.ViewGenerator
import lk.android.ui.thread.UIThread
import lk.anko.extensions.anko
import lk.kotlin.jvm.utils.async.Async
import lk.kotlin.jvm.utils.async.invokeOn
import lk.kotlin.jvm.utils.random.random
import lk.kotlin.okhttp.thenOnSuccess
import okhttp3.Request
import org.jetbrains.anko.*

class ImageLoadingVG() : ViewGenerator {

    override fun invoke(access: ActivityAccess): View = access.context.anko().scrollView {

        isFillViewport = true

        verticalLayout {

            imageView {
                Request.Builder()
                        .url("http://lorempixel.com/${(200..600).random()}/${(200..600).random()}")
                        .lambdaBitmap()
                        .thenOnSuccess(UIThread) {
                            setImageBitmap(it)
                        }
                        .invokeOn(Async)
            }.lparams(matchParent, dip(300))

        }.lparams(matchParent, wrapContent)
    }
}