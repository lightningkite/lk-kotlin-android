package lk.everything.deprecated.anko

import android.view.View

@Deprecated("Use the new validation system in the observable package.")
class ValidationIssue(
        val view: View? = null,
        val message: String = ""
)