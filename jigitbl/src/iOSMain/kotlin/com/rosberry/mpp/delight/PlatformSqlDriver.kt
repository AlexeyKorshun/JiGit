/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.mpp.delight

import com.rosberry.mpp.jigitbl.JigitDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

/**
 * @author Alexei Korshun on 07/03/2019.
 */
actual class SqlDriverFactory {

    actual fun create(name: String): SqlDriver {
        return NativeSqliteDriver(JigitDatabase.Schema, name)
    }
}