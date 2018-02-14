@file:JvmName("Deprecated")

package lk.everything.deprecated.lambda

import lk.kotlin.utils.lambda.then

/**
 *
 * Created by joseph on 11/14/16.
 */
@Deprecated("Use 'then' instead.", ReplaceWith("then(mapper)"))
fun <A, B> (() -> A).map(mapper: (A) -> B): () -> B = then(mapper)

@Deprecated("Use 'then' instead.", ReplaceWith("then(mapper)"))
fun <A, B> (() -> A).transform(mapper: (A) -> B): () -> B = then(mapper)