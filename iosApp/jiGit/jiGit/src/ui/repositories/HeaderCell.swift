//
// Created by Aleksei Korshun on 2019-03-01.
// Copyright (c) 2019 Aleksei Korshun. All rights reserved.
//

import UIKit
import Framezilla

class HeaderCell: UICollectionViewCell {

    private lazy var headerView: UILabel = {
        let view = UILabel()
        view.textColor = .black
        view.text = "Repositories"
        view.font = UIFont.boldSystemFont(ofSize: 16.0)
        return view
    }()

    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }

    override init(frame: CGRect) {
        super.init(frame: frame)

        contentView.addSubviews(headerView)
    }

    override func layoutSubviews() {
        super.layoutSubviews()

        headerView.configureFrame { maker in
            maker.sizeToFit()
                 .centerY()
        }
    }
}
