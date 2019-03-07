/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.mpp.delight

import android.content.Context
import com.rosberry.mpp.jigitbl.JigitDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

/**
 * @author Alexei Korshun on 07/03/2019.
 */
actual class SqlDriverFactory(
        private val context: Context
) {

    actual fun create(name: String): SqlDriver {
        return AndroidSqliteDriver(JigitDatabase.Schema, context, name)
    }
}