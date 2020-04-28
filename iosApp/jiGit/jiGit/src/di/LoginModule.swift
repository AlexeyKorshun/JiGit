//
//  LoginModule.swift
//  jiGit
//
//  Created by Aleksei Korshun on 13/02/2019.
//  Copyright © 2019 Aleksei Korshun. All rights reserved.
//
import FRAMEWORK

class LoginModule {

    private let router: LoginRouter

    init(router: LoginRouter) {
        self.router = router
    }

    func provideViewController() -> LoginViewController {
        let presenter = providePresenter()
        let viewController = LoginViewController(presenter)
        presenter.view = viewController
        return viewController
    }
    
    func providePresenter() -> LoginPresenter {
        return LoginPresenter(authInteractor: provideAuthInteractor(), router: router)
    }
    
    func provideAuthInteractor() -> AuthInteractor {
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
