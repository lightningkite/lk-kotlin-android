package com.lightningkite.kotlincomponents

import lk.kotlin.jackson.jacksonObject
import lk.kotlin.okhttp.OkHttpApi
import lk.kotlin.okhttp.TypedResponse
import lk.kotlin.okhttp.jackson.lambdaJackson

/**
 * An example API.
 * Created by josep on 11/10/2016.
 */
object ExampleAPI : OkHttpApi {
    override val baseUrl: String = "https://jsonplaceholder.typicode.com"

    val testEmail = "test@gmail.com"
    val testPassword = "testpass"

    fun getPosts() = requestBuilder("/posts")
            .get()
            .lambdaJackson<List<Post>>()

    fun login(email: String, password: String): () -> TypedResponse<LoginData> = {

        //emulate server work
        Thread.sleep(400L)

        if (email == testEmail && password == testPassword) {
            //Login success!
            TypedResponse(200, LoginData(userId = 0, jwt = "testjwt", email = testEmail))
        } else {
            //Login failure
            TypedResponse(400, null, errorBytes = jacksonObject("error" to "Your credentials are invalid.  The test login is 'test@gmail.com' and 'testpass'."
            ).toString().toByteArray())
        }
    }
}

class Post(
        var userId: Long = -1,
        var id: Long = -1,
        var title: String = "",
        var body: String = ""
)

class LoginData(
        var userId: Long = -1,
        var jwt: String = "",
        var email: String = ""
)