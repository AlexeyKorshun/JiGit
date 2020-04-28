//
//  AppDelegate.swift
//  jiGit
//
//  Created by Aleksei Korshun on 07/02/2019.
//  Copyright © 2019 Aleksei Korshun. All rights reserved.
//

import UIKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        
        let iDi = IDi()
        let rootViewController: NavigationViewController = iDi.appNavigationViewController
        rootViewController.setViewControllers([iDi.provideLaunchViewController()], animated: false)
        
        window = UIWindow(frame: UIScreen.main.bounds)
        window?.rootViewController = rootViewController
        window?.makeKeyAndVisible()
        
        return true
    }
}
