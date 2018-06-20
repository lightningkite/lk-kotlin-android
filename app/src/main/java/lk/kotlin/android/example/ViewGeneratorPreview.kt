package lk.kotlin.android.example

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import lk.android.activity.access.ActivityAccess

class ViewGeneratorPreview(context: Context) : FrameLayout(context) {

    //Create an ActivityAccess object that's a placeholder for a real one
    val access = object : ActivityAccess {
        override val activity: Activity? = null
        override val context: Context = context
        override val onResume: MutableCollection<() -> Unit> = ArrayList()
        override val onPause: MutableCollection<() -> Unit> = ArrayList()
        override val onSaveInstanceState: MutableCollection<(outState: Bundle) -> Unit> = ArrayList()
        override val onLowMemory: MutableCollection<() -> Unit> = ArrayList()
        override val onDestroy: MutableCollection<() -> Unit> = ArrayList()
        override val onActivityResult: MutableCollection<(request: Int, result: Int, data: Intent?) -> Unit> = ArrayList()
        override val onBackPressed: MutableList<() -> Boolean> = ArrayList()
        override val onNewIntent: MutableCollection<(Intent) -> Unit> = ArrayList()

        override fun prepareOnResult(presetCode: Int, onResult: (Int, Intent?) -> Unit): Int = 0
        override fun requestPermissions(permission: Array<String>, onResult: (Map<String, Int>) -> Unit) {}
        override fun requestPermission(permission: String, onResult: (Boolean) -> Unit) {}

    }

    init {
        addView(lk.kotlin.android.example.activity.access.ActivityAccessVG().invoke(access))
    }
}