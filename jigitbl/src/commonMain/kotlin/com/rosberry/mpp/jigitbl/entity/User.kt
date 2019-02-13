/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.mpp.jigitbl.entity

/**
 * @author Alexei Korshun on 13/02/2019.
 */
@kotlinx.serialization.Serializable
data class User(
        val login: String,
        val id: Int
)