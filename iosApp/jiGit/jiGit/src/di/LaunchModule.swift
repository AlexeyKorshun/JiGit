//
//  LaunchModule.swift
//  jiGit
//
//  Created by Aleksei Korshun on 08/02/2019.
//  Copyright © 2019 Aleksei Korshun. All rights reserved.
//

import FRAMEWORK

class LaunchModule {

    private let router: LaunchRouter

    init(_ router: LaunchRouter) {
        self.router = router
    }

    func provideViewController() -> LaunchViewController {
        return LaunchViewController(providePresenter())
    }

    private func providePresenter() -> LaunchPresenter {
        return LaunchPresenter(provideAuthInteractor(), router)
    }

    private func provideAuthInteractor() -> AuthInteractor {
        return AuthInteractor(authRepository: provideAuthRepository())
    }

    private func provideAuthRepository() -> AuthRepository {
        return AuthRepository(authManager: provideAuthManager(), authApi: provideAuthApi())
    }

    private func provideAuthManager() -> AuthManager {
        return AuthManager(factory: providePreferenceFactory())
    }

    private func providePreferenceFactory() -> PreferencesFactory {
        return PlatformPreferences.Factory()
    }

    private func provideAuthApi() -> AuthApi {
        return AuthApi(client: provideHttpClient())
    }

    private func provideHttpClient() -> Ktor_client_coreHttpClient {
        return KtorFactory().createKtor()
    }
}
