package lk.anko.view.controllers.deprecated.implementations



import android.os.Bundle
import android.view.View
import lk.android.activity.access.AccessibleActivity
import lk.anko.view.controllers.deprecated.ViewController
import lk.anko.view.controllers.deprecated.containers.VCContainer

/**
 * All activities hosting [ViewController]s must be extended from this one.
 * It handles the calling of other activities with [onActivityResult], the attaching of a
 * [VCContainer], and use the back button on the [VCContainer].
 * Created by jivie on 10/12/15.
 */
@Deprecated("Deprecated along with ViewControllers in general.  Just use AccessibleActivity with an onCreate instead.")
abstract class VCActivity : AccessibleActivity() {

    abstract val viewController: ViewController

    var vcView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vcView = viewController.make(this)
        setContentView(vcView!!)
    }

    override fun onBackPressed() {
        if (!onBackPressed.reversed().any { it.invoke() }) {
            viewController.onBackPressed { super.onBackPressed() }
        }
    }

    override fun onDestroy() {
        if (vcView != null) {
            viewController.unmake(vcView!!)
            vcView = null
        }
        super.onDestroy()
    }
}