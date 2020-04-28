//
// Created by Aleksei Korshun on 2019-02-28.
// Copyright (c) 2019 Aleksei Korshun. All rights reserved.
//

import UIKit

extension UIActivityIndicatorView {

    func showLoading(_ isLoading: Bool) {
        if (isLoading) {
            self.startAnimating()
        } else {
            self.stopAnimating()
        }
    }
}