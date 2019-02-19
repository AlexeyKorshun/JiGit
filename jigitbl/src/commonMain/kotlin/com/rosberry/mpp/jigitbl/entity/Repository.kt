/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.mpp.jigitbl.entity

import kotlinx.serialization.Serializable

/**
 * @author Alexei Korshun on 19/02/2019.
 */
@Serializable
data class Repository(
        val id: Int,
        val name: String,
        val owner: User
)