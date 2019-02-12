/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.jigit.ui.login

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.rosberry.android.core.ui.AppFragment
import com.rosberry.android.extensions.doOnTextChanged
import com.rosberry.android.extensions.show
import com.rosberry.android.jigit.R
import com.rosberry.android.jigit.di.Scopes
import com.rosberry.android.jigit.presentation.login.LoginPresenter
import com.rosberry.android.jigit.presentation.login.LoginView
import kotlinx.android.synthetic.main.fragment_login.*
import toothpick.Toothpick

/**
 * @author Alexei Korshun on 06/02/2019.
 */
class LoginFragment : AppFragment(), LoginView {

    @InjectPresenter
    lateinit var presenter: LoginPresenter

    @ProvidePresenter
    fun providePresenter(): LoginPresenter {
        return Toothpick.openScopes(Scopes.APP, scopeName())
            .getInstance(LoginPresenter::class.java)
    }

    override val layoutRes: Int
        get() = R.layout.fragment_login

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginButton.setOnClickListener { presenter.clickLogin(loginView.text.toString(), passwordView.text.toString()) }

        loginView.doOnTextChanged { text, _, _, _ ->
            presenter.onCredentialChanged(text.toString(), passwordView.text.toString())
        }

        passwordView.doOnTextChanged { text, _, _, _ ->
            presenter.onCredentialChanged(loginView.text.toString(), text.toString())
        }
    }

    override fun isLoginEnable(isEnabled: Boolean) {
        loginButton.isEnabled = isEnabled
    }

    override fun isLoading(isLoading: Boolean) {
        showProgressDialog(isLoading)
    }

    override fun isError(isError: Boolean, message: String) {
        errorMessageView.text = message
        errorMessageView.show(isError)
    }
}