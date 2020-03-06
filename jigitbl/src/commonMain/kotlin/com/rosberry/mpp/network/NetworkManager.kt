/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.mpp.network

/**
 * @author Alexei Korshun on 07/03/2019.
 */
interface NetworkManager {

    suspend fun isInternetAvailable(): Boolean

    interface Factory {

        fun create(): NetworkManager
    }
}