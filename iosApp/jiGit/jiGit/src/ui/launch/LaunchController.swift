//
//  LauchController.swift
//  jiGit
//
//  Created by Aleksei Korshun on 08/02/2019.
//  Copyright © 2019 Aleksei Korshun. All rights reserved.
//

import FRAMEWORK
import UIKit

class LaunchViewController: UIViewController {
    
    private let presenter: LaunchPresenter
    
    init(_ presenter: LaunchPresenter) {
        self.presenter = presenter
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("Can't create with coder")
    }
    
    override func viewDidAppear(_ animated: Bool) {
        presenter.onAttach()
    }
}
