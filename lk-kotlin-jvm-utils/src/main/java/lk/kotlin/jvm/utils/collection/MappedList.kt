@file:JvmName("LkKotlinJvmUtils")
@file:JvmMultifileClass

package lk.kotlin.jvm.utils.collection

import lk.kotlin.utils.collection.MappedMutableList
import java.util.*

inline fun <S, T> MutableList<S>.mappingMutableHash(crossinline mapper: (S) -> T): MutableList<T> {
    val forward = WeakHashMap<S, T>()
    val reverse = WeakHashMap<T, S>()
    return MappedMutableList<S, T>(
            this,
            mapper = {
                val current = forward[it]
                if (current != null) return@MappedMutableList current
                val mapped = mapper(it)
                forward.put(it, mapped)
                reverse.put(mapped, it)
                mapped
            },
            reverseMapper = {
                reverse[it]!!
            }
    )
}