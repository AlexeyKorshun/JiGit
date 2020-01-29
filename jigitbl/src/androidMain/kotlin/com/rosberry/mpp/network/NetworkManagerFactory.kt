/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.mpp.network

import java.net.InetAddress

/**
 * @author Alexei Korshun on 07/03/2019.
 */
actual class NetworkManagerFactory : NetworkManager.Factory {

    override fun create(): NetworkManager {
        return PlatformNetworkManager()
    }
}

private class PlatformNetworkManager : NetworkManager {

    override suspend fun isInternetAvailable(): Boolean {
        return try {
            InetAddress.getByName("google.com")
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
