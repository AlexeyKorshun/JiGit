//
//  NavigationViewController.swift
//  jiGit
//
//  Created by Aleksei Korshun on 08/02/2019.
//  Copyright © 2019 Aleksei Korshun. All rights reserved.
//

import UIKit

typealias Router = LaunchRouter & LoginRouter

class NavigationViewController: UINavigationController {
    
    init() {
        super.init(nibName: nil, bundle: nil)
        isNavigationBarHidden = true
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("Can't create instance with coder")
    }
    
    override func viewDidLoad() {
        view.backgroundColor = UIColor.white
    }
}

extension NavigationViewController: Router {

    func navigateToRepositoriesScreen() {
        let repositoriesModule = RepositoriesModule()
        let repositoriesViewController = repositoriesModule.provideViewController()

        self.setViewControllers([repositoriesViewController], animated: true)
    }

    func navigateToLoginScreen() {
        let loginModule = LoginModule(router: self)
        let loginViewController = loginModule.provideViewController()
        
        self.setViewControllers([loginViewController], animated: true)
    }
}
