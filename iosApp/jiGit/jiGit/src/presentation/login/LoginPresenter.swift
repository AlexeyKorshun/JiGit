//
//  LoginPresenter.swift
//  jiGit
//
//  Created by Aleksei Korshun on 08/02/2019.
//  Copyright © 2019 Aleksei Korshun. All rights reserved.
//

import FRAMEWORK

class LoginPresenter {

    private let authInteractor: AuthInteractor
    private let router: LoginRouter

    weak var view: LoginViewController?

    init(authInteractor: AuthInteractor, router: LoginRouter) {
        self.authInteractor = authInteractor
        self.router = router
    }

    func clickLoginButton(username: String, password: String) {
        authInteractor.auth(username: username, password: password, onSuccess: onUserLogin, onError: onLoginFailure)
        view?.isLoading(isLoading: true)
        view?.isError(isError: false)
    }

    func credentialChanged(username: String, password: String) {
        let isValid: Bool = authInteractor.isValidCredentials(username: username, password: password)
        view?.isLoginEnabled(isEnabled: isValid)
    }

    func onUserLogin(user: User) {
        view?.isLoading(isLoading: false)
        router.navigateToRepositoriesScreen()
    }

    func onLoginFailure(throwable: KotlinThrowable) {
        view?.isLoading(isLoading: false)
        view?.isError(isError: true, message: throwable.message)
    }
}

protocol LoginRouter {

    func navigateToRepositoriesScreen()
}
