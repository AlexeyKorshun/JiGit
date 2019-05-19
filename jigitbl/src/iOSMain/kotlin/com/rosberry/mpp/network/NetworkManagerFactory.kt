/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.mpp.network

/**
 * @author Alexei Korshun on 07/03/2019.
 */
actual class NetworkManagerFactory : NetworkManager.Factory {

    override fun create(): NetworkManager = PlatformNetworkManager()
}

class PlatformNetworkManager : NetworkManager {

    override suspend fun isInternetAvailable(): Boolean = TODO("implement when start iOS")
}