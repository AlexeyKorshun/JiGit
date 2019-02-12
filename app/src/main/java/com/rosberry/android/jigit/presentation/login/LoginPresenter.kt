/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.jigit.presentation.login

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.rosberry.mpp.jigitbl.domain.AuthInteractor
import javax.inject.Inject

/**
 * @author Alexei Korshun on 07/02/2019.
 */
@InjectViewState
class LoginPresenter @Inject constructor(
        private val authInteractor: AuthInteractor
) : MvpPresenter<LoginView>() {

    fun clickLogin(username: String, password: String) {
        viewState.isLoading(true)
        viewState.isError(false)
        authInteractor.auth(username, password, this::onResult, this::onError)
    }

    private fun onResult(result: String) {
        viewState.isLoading(false)
        Log.d("MPP_TEST", result)
    }

    private fun onError(error: Throwable) {
        error.printStackTrace()
        viewState.isLoading(false)
        viewState.isError(true, error.localizedMessage)
    }

    fun onCredentialChanged(username: String, password: String) {
        viewState.isError(false)
        val isValid = authInteractor.isValidCredentials(username, password)
        viewState.isLoginEnable(isValid)
    }
}