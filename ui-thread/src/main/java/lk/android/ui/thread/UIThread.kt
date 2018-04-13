package lk.android.ui.thread



import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

/**
 * An executor that executes the [Runnable]s it's given on the UI thread using a handler.
 * Created by jivie on 9/2/15.
 */
object UIThread : Executor {
    val uiHandler: Handler = Handler(Looper.getMainLooper())

    override fun execute(p0: Runnable) {
        uiHandler.post(p0)
    }
}
