//
// Created by Aleksei Korshun on 2019-02-22.
// Copyright (c) 2019 Aleksei Korshun. All rights reserved.
//

import UIKit
import Framezilla
import FRAMEWORK

class RepositoriesViewController: UIViewController {

    private let sectionInsets = UIEdgeInsets(top: 0.0, left: 10.0, bottom: 10.0, right: 0.0)
    private let cellIdentifier = "repositoryCell"
    private let headerIdentifier = "repositoryHeader"
    private var repositories: [Repository] = []

    private let presenter: RepositoriesPresenter

    private lazy var repositoriesView: UICollectionView = {
        let layout = UICollectionViewFlowLayout()
        layout.scrollDirection = .vertical
        layout.sectionInset = sectionInsets

        let view: UICollectionView = UICollectionView(frame: .zero, collectionViewLayout: layout)
        view.backgroundColor = .clear
        view.register(RepositoryViewCell.self, forCellWithReuseIdentifier: cellIdentifier)
        view.register(HeaderCell.self, forSupplementaryViewOfKind: UICollectionView.elementKindSectionHeader, withReuseIdentifier: headerIdentifier)
        view.delegate = self
        view.dataSource = self
        view.isHidden = true
        return view
    }()

    private lazy var progressIndicator: UIActivityIndicatorView = {
        let view = UIActivityIndicatorView(style: .gray)
        return view
    }()

    private lazy var errorView: UILabel = {
        let view = UILabel()
        view.textColor = .red
        view.numberOfLines = 0
        view.isHidden = true
        return view
    }()

    init(_ presenter: RepositoriesPresenter) {
        self.presenter = presenter
        super.init(nibName: nil, bundle: nil)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("Can't create with NSCoder")
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        view.addSubviews(progressIndicator, repositoriesView, errorView)
    }

    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()

        progressIndicator.configureFrame { maker in
            maker.size(width: 24, height: 24)
                 .center()
        }

        repositoriesView.configureFrame { maker in
            maker.top()
                 .bottom()
                 .left()
                 .right()
        }

        errorView.configureFrame { maker in
            maker.center()
                .left()
                .right()
        }
    }

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        presenter.onAttach()
    }

    func isLoading(isLoading: Bool) {
        progressIndicator.showLoading(isLoading)
    }

    func showRepositories(data: [Repository]) {
        repositories = data
        repositoriesView.reloadData()
        repositoriesView.isHidden = false
    }

    func isError(isError: Bool, message: String? = "") {
        if (message != nil) {
            errorView.text = message
        } else {
            errorView.text = "Error"
        }
        errorView.sizeToFit()
        errorView.isHidden = !isError
    }
}

extension RepositoriesViewController: UICollectionViewDataSource {

    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }

    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return repositories.count
    }

    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath)
                    -> UICollectionViewCell {

        let title: String = repositories[indexPath.row].name

        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: cellIdentifier, for: indexPath) as! RepositoryViewCell
        cell.titleView.text = title
        cell.titleView.sizeToFit()
        return cell
    }

    func collectionView(_ collectionView: UICollectionView, viewForSupplementaryElementOfKind kind: String, at indexPath: IndexPath) -> UICollectionReusableView {
        if (kind == UICollectionView.elementKindSectionHeader) {
            let headerView = collectionView.dequeueReusableSupplementaryView(ofKind: kind, withReuseIdentifier: headerIdentifier, for: indexPath)
            return headerView
        }
        fatalError()
    }
}

extension RepositoriesViewController: UICollectionViewDelegateFlowLayout {

    func collectionView(_ collectionView: UICollectionView,
                        layout collectionViewLayout: UICollectionViewLayout,
                        sizeForItemAt indexPath: IndexPath) -> CGSize {

        let height = 52.0.value
        let width = view.frame.width

        return CGSize(width: width, height: height)
    }

    func collectionView(_ collectionView: UICollectionView,
                        layout collectionViewLayout: UICollectionViewLayout,
                        insetForSectionAt section: Int) -> UIEdgeInsets {
        return sectionInsets
    }

    func collectionView(_ collectionView: UICollectionView,
                        layout collectionViewLayout: UICollectionViewLayout,
                        minimumLineSpacingForSectionAt section: Int) -> CGFloat {
        return 0
    }

    func collectionView(_ collectionView: UICollectionView,
                        layout collectionViewLayout: UICollectionViewLayout,
                        referenceSizeForHeaderInSection section: Int) -> CGSize {

        let height = 52.0.value
        let width = view.frame.width
        return CGSize(width: width, height: height)
    }
}
