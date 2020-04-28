//
// Created by Aleksei Korshun on 2019-02-28.
// Copyright (c) 2019 Aleksei Korshun. All rights reserved.
//

import UIKit
import FRAMEWORK
import Framezilla

class RepositoryViewCell: UICollectionViewCell {

    lazy var titleView: UILabel = {
        let view = UILabel()
        return view
    }()

    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }

    override init(frame: CGRect) {
        super.init(frame: frame)

        contentView.addSubviews(titleView)
    }

    override func layoutSubviews() {
        super.layoutSubviews()

        titleView.configureFrame { maker in
            maker.left(inset: 16)
                 .centerY()
        }
    }
}
