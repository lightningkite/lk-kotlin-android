@file:JvmName("LkAndroidExtensions")
@file:JvmMultifileClass

package lk.android.extensions



import android.graphics.PorterDuff
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView

/**
 * Extensions functions on EditText.
 * Created by jivie on 5/2/16.
 */

fun EditText.resetCursorColor() {
    try {
        val f = TextView::class.java.getDeclaredField("mCursorDrawableRes")
        f.isAccessible = true
        f.set(this, 0)
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}

inline fun EditText.onImeAction(crossinline action: (text: String) -> Unit) {
    setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
        if ((event?.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
            action(text.toString())
            return@OnKeyListener true
        }
        false
    })
    setOnEditorActionListener({ v, actionId, event ->
        action(text.toString())
        true
    })
}

inline fun EditText.onDone(crossinline action: (text: String) -> Unit) {
    imeOptions = EditorInfo.IME_ACTION_DONE
    onImeAction {
        hideSoftInput()
        action(text.toString())
    }
}

inline fun EditText.onSend(crossinline action: (text: String) -> Unit) {
    imeOptions = EditorInfo.IME_ACTION_SEND
    onImeAction {
        hideSoftInput()
        action(text.toString())
    }
}

fun EditText.setCursorColor(color: Int) {
    try {
        val fCursorDrawableRes = TextView::class.java.getDeclaredField("mCursorDrawableRes")
        fCursorDrawableRes.setAccessible(true)
        val mCursorDrawableRes = fCursorDrawableRes.getInt(this)
        val fEditor = TextView::class.java.getDeclaredField("mEditor")
        fEditor.isAccessible = true
        val editor = fEditor.get(this)
        val clazz = editor.javaClass
        val fCursorDrawable = clazz.getDeclaredField("mCursorDrawable")
        fCursorDrawable.isAccessible = true
        val drawables = arrayOf(
                context.resources.getDrawableCompat(mCursorDrawableRes).mutate().apply { setColorFilter(color, PorterDuff.Mode.SRC_IN) },
                context.resources.getDrawableCompat(mCursorDrawableRes).mutate().apply { setColorFilter(color, PorterDuff.Mode.SRC_IN) }
        )
        fCursorDrawable.set(editor, drawables)
    } catch (ignored: Throwable) {
    }
}
