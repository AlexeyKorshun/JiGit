/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.mpp.delight

import com.squareup.sqldelight.db.SqlDriver

/**
 * @author Alexei Korshun on 07/03/2019.
 */
expect class SqlDriverFactory {

    fun create(name: String): SqlDriver
}