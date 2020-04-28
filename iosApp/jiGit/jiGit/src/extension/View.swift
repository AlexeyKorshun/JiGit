//
//  View.swift
//  jiGit
//
//  Created by Aleksei Korshun on 08/02/2019.
//  Copyright © 2019 Aleksei Korshun. All rights reserved.
//

import UIKit

extension UIView {
    
    func addSubviews(_ views: UIView...) {
        for view in views {
            self.addSubview(view)
        }
    }
}
