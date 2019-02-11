/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.jigit

import androidx.fragment.app.Fragment
import com.rosberry.android.jigit.ui.login.LoginFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

/**
 * @author Alexei Korshun on 06/02/2019.
 */
object Screens {

    class LoginScreen : SupportAppScreen() {

        override fun getFragment(): Fragment = LoginFragment()
    }
}