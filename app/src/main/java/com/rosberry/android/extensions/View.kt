/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.extensions

import android.view.View

/**
 * @author Alexei Korshun on 12/02/2019.
 */

fun View.show(isShow: Boolean) {
    if (isShow) this.visibility = View.VISIBLE
    else this.visibility = View.GONE
}