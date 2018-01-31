@file:JvmName("LkKotlinJvmUtils")
@file:JvmMultifileClass

package com.lightningkite.kotlin.collection

/**
 * Various extensions functions for lists.
 * Created by jivie on 4/26/16.
 */

inline fun <E> List<E>.random(): E {
    return this[Math.random().times(size).toInt()]
}