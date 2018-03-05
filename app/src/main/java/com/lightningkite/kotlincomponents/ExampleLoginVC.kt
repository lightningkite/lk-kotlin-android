package com.lightningkite.kotlincomponents

import android.view.View
import android.widget.Button
import lk.android.activity.access.ActivityAccess
import lk.android.mighty.view.ViewGenerator
import lk.android.dialogs.infoDialog
import lk.android.extensions.FullInputType
import lk.android.extensions.onDone
import lk.android.observable.bindString
import lk.android.observable.validation.bindError
import lk.android.observable.validation.validation
import lk.android.observable.validation.withValidation
import lk.android.ui.thread.UIThread
import lk.anko.activity.access.anko
import lk.anko.animations.observable.progressLayout
import lk.anko.extensions.textInputEditText
import lk.kotlin.jvm.utils.async.Async
import lk.kotlin.jvm.utils.async.invokeOn
import lk.kotlin.observable.property.MutableObservableProperty
import lk.kotlin.observable.property.StandardObservableProperty
import lk.kotlin.observable.property.jvm.captureProgress
import lk.kotlin.okhttp.TypedResponse
import lk.kotlin.okhttp.thenOnFailure
import lk.kotlin.okhttp.thenOnSuccess
import lk.kotlin.utils.text.isEmail
import org.jetbrains.anko.*
import org.jetbrains.anko.design.textInputLayout

/**
 * An example login view.
 * Created by joseph on 11/2/17.
 */
class ExampleLoginVC(val onLogin: (LoginData) -> Unit) : ViewGenerator {

    override fun toString(): String = "Example Login"

    //Business Logic

    val emailObs = StandardObservableProperty("").withValidation {
        when {
            it.isBlank() -> resource(R.string.validation_email_blank)
            !it.isEmail() -> { resources -> resources.getString(R.string.validation_email_invalid, it) }
            else -> null
        }
    }
    val passwordObs = StandardObservableProperty("").withValidation {
        when {
            it.isBlank() -> resource(R.string.validation_password_empty)
            it.length < 8 -> resource(R.string.validation_password_short)
            else -> null
        }
    }

    fun isValid() = listOf(emailObs, passwordObs).all { it.validation!!.isValid() }
    fun attemptLogin() = ExampleAPI.login(email = emailObs.value, password = passwordObs.value)


    //Views

    override fun invoke(access: ActivityAccess): View = access.anko().scrollView {
        isFillViewport = true

        verticalLayout {
            padding = dip(8)

            var loginButton: Button? = null

            textInputLayout {
                hint = context.getString(R.string.email)
                textInputEditText {
                    inputType = FullInputType.EMAIL
                    bindString(emailObs)
                    bindError(emailObs)
                }
            }.lparams(matchParent, wrapContent) { margin = dip(8) }

            textInputLayout {
                hint = context.getString(R.string.password)
                textInputEditText {
                    inputType = FullInputType.PASSWORD
                    bindString(passwordObs)
                    bindError(passwordObs)
                    onDone {
                        loginButton!!.performClick()
                    }
                }
            }.lparams(matchParent, wrapContent) { margin = dip(8) }

            progressLayout { runningObs: MutableObservableProperty<Boolean> ->
                button {
                    loginButton = this
                    text = context.getString(R.string.log_in)
                    setOnClickListener {
                        if (isValid()) {
                            attemptLogin()
                                    .captureProgress(runningObs, UIThread) //sets the observable to true when task is started, false when complete
                                    .thenOnSuccess(UIThread) { loginData: LoginData ->
                                        onLogin.invoke(loginData)
                                    }
                                    .thenOnFailure(UIThread) { response: TypedResponse<LoginData> ->
                                        context.infoDialog(message = "You failed to log in.  Response from server: \n${response.errorString}")
                                    }
                                    .invokeOn(Async)
                        }
                    }
                }.lparams(matchParent, wrapContent) { margin = dip(8) }
            }.lparams(matchParent, wrapContent)
        }.lparams(matchParent, wrapContent)
    }
}