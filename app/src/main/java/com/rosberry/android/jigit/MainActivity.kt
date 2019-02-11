package com.rosberry.android.jigit

import android.os.Bundle
import com.rosberry.android.core.ui.AppActivity
import com.rosberry.android.jigit.di.Scopes
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import toothpick.Toothpick
import javax.inject.Inject

class MainActivity : AppActivity() {

    @Inject
    override lateinit var navigatorHolder: NavigatorHolder

    override val layoutRes: Int
        get() = R.layout.activity_main

    override val navigator: Navigator by lazy {
        SupportAppNavigator(this, supportFragmentManager, android.R.id.content)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.inject(this@MainActivity, Toothpick.openScope(Scopes.APP))
        super.onCreate(savedInstanceState)
    }
}
