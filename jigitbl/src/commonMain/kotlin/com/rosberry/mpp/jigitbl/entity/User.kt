/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.mpp.jigitbl.entity

import kotlinx.serialization.Serializable

/**
 * @author Alexei Korshun on 13/02/2019.
 */
@Serializable
data class User(
        val login: String,
        val id: Int
)