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
        authInteractor.auth(username, password) { onResult(it) }
    }

    private fun onResult(result: String) {
        Log.d("MPP_TEST", result)
    }
}