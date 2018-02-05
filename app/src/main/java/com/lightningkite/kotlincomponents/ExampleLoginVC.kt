package com.lightningkite.kotlincomponents

import android.view.View
import android.widget.Button
import com.lightningkite.kotlin.anko.FullInputType
import com.lightningkite.kotlin.anko.activity.ActivityAccess
import com.lightningkite.kotlin.anko.activity.ViewGenerator
import com.lightningkite.kotlin.anko.activity.anko
import com.lightningkite.kotlin.anko.activity.dialog.infoDialog
import com.lightningkite.kotlin.anko.async.UIThread
import com.lightningkite.kotlin.anko.full.captureProgress
import com.lightningkite.kotlin.anko.observable.bindString
import com.lightningkite.kotlin.anko.observable.progressLayout
import com.lightningkite.kotlin.anko.observable.validation.bindError
import com.lightningkite.kotlin.anko.observable.validation.validation
import com.lightningkite.kotlin.anko.observable.validation.withValidation
import com.lightningkite.kotlin.anko.onDone
import com.lightningkite.kotlin.anko.textInputEditText
import com.lightningkite.kotlin.async.Async
import com.lightningkite.kotlin.async.invokeOn
import com.lightningkite.kotlin.networking.TypedResponse
import com.lightningkite.kotlin.networking.thenOnFailure
import com.lightningkite.kotlin.networking.thenOnSuccess
import com.lightningkite.kotlin.observable.property.MutableObservableProperty
import com.lightningkite.kotlin.observable.property.StandardObservableProperty
import com.lightningkite.kotlin.text.isEmail
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

    override fun invoke(access: ActivityAccess): View = access.anko {
        scrollView {
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
}