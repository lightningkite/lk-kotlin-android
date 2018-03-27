package lk.kotlin.android.example

import android.os.Bundle
import lk.android.activity.access.AccessibleActivity

/**
 * When using the Mighty View style, we don't have very big activities.  This is sufficient.
 */
class MainActivity : AccessibleActivity() {

    companion object {
        var main = MainVG()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(main.invoke(this))
    }
}
