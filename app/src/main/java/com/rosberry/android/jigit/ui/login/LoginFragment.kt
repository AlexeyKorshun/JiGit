/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.jigit.ui.login

import com.arellomobile.mvp.presenter.InjectPresenter
import com.rosberry.android.core.ui.AppFragment
import com.rosberry.android.jigit.R
import com.rosberry.android.jigit.presentation.login.LoginPresenter
import com.rosberry.android.jigit.presentation.login.LoginView

/**
 * @author Alexei Korshun on 06/02/2019.
 */
class LoginFragment: AppFragment(), LoginView {

    @InjectPresenter
    lateinit var presenter: LoginPresenter

    override val layoutRes: Int
        get() = R.layout.fragment_login
}