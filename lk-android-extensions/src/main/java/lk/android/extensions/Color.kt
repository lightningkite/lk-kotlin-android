@file:JvmName("LkAndroidExtensions")
@file:JvmMultifileClass

package lk.android.extensions



import android.graphics.Color

/**
 * Various functions for manipulating colors.
 * Created by jivie on 5/23/16.
 */

/**
 * Returns the color with the new alpha value.
 */
fun Int.colorAlpha(alpha: Int): Int {
    return (this and 0x00FFFFFF) or (alpha shl 24)
}

/**
 * Returns the color with the new alpha value.
 * @param alpha How transparent the color is, from 0 (totally transparent) to 1 (totally opaque).
 */
fun Int.colorAlpha(alpha: Float): Int {
    return (this and 0x00FFFFFF) or ((alpha.coerceIn(0f, 1f) * 0xFF).toInt() shl 24)
}

/**
 * Multiplies the color components (besides alpha), darkening it.
 * @param value A value between 0 (white becomes black) and 1 (no effect).
 */
fun Int.colorMultiply(value: Double): Int {
    return Color.argb(
            Color.alpha(this),
            (Color.red(this) * value).toInt().coerceIn(0, 255),
            (Color.green(this) * value).toInt().coerceIn(0, 255),
            (Color.blue(this) * value).toInt().coerceIn(0, 255)
    )
}

/**
 * Multiplies the color components (besides alpha), darkening it.
 * @param value A value between 0 (white becomes black) and 1 (no effect).
 */
fun Int.colorMultiply(value: Float): Int {
    return Color.argb(
            Color.alpha(this),
            (Color.red(this) * value).toInt().coerceIn(0, 255),
            (Color.green(this) * value).toInt().coerceIn(0, 255),
            (Color.blue(this) * value).toInt().coerceIn(0, 255)
    )
}

/**
 * Adds to the color components (besides alpha), lightening it.
 * @param value A value between 0 (no effect) and 1 (black becomes white).
 */
fun Int.colorAdd(value: Double): Int {
    return Color.argb(
            Color.alpha(this),
            (Color.red(this) + (value * 0xFF).toInt()).coerceIn(0, 255),
            (Color.green(this) + (value * 0xFF).toInt()).coerceIn(0, 255),
            (Color.blue(this) + (value * 0xFF).toInt()).coerceIn(0, 255)
    )
}

/**
 * Adds to the color components (besides alpha), lightening it.
 * @param value A value between 0 (no effect) and 1 (black becomes white).
 */
fun Int.colorAdd(value: Float): Int {
    return Color.argb(
            Color.alpha(this),
            (Color.red(this) + (value * 0xFF).toInt()).coerceIn(0, 255),
            (Color.green(this) + (value * 0xFF).toInt()).coerceIn(0, 255),
            (Color.blue(this) + (value * 0xFF).toInt()).coerceIn(0, 255)
    )
}