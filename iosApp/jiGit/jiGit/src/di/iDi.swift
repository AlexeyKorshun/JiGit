//
//  iDi.swift
//  jiGit
//
//  Created by Aleksei Korshun on 13/02/2019.
//  Copyright © 2019 Aleksei Korshun. All rights reserved.
//
import UIKit

class IDi {
    
    let appNavigationViewController: NavigationViewController = NavigationViewController()
    
    func provideLaunchViewController() -> LaunchViewController {
        return LaunchModule(appNavigationViewController).provideViewController()
    }
}
