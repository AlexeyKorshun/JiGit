//
//  LaunchPresenter.swift
//  jiGit
//
//  Created by Aleksei Korshun on 08/02/2019.
//  Copyright © 2019 Aleksei Korshun. All rights reserved.
//

import FRAMEWORK

class LaunchPresenter {
 
    private let authInteractor: AuthInteractor
    private let router: LaunchRouter
    
    init(_ authInteractor: AuthInteractor, _ router: LaunchRouter) {
        self.authInteractor = authInteractor
        self.router = router
    }
    
    func onAttach() {
        if (!authInteractor.isAuth()) {
            router.navigateToLoginScreen()
        } else {
            router.navigateToRepositoriesScreen()
        }
    }
}

protocol LaunchRouter {
    
    func navigateToLoginScreen()

    func navigateToRepositoriesScreen()
}
