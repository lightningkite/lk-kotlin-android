package lk.kotlin.android.example

import android.content.res.Resources

/**
 *
 * Created by joseph on 3/27/18.
 */
interface HasTitle {
    fun getTitle(resources: Resources): String
}