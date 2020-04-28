//
// Created by Aleksei Korshun on 2019-02-22.
// Copyright (c) 2019 Aleksei Korshun. All rights reserved.
//

import Foundation
import FRAMEWORK

class RepositoriesPresenter {

    private let repositoriesInteractor: RepositoriesInteractor

    weak var view: RepositoriesViewController? = nil

    init(_ repositoriesInteractor: RepositoriesInteractor) {
        self.repositoriesInteractor = repositoriesInteractor
    }

    func onAttach() {
        loadRepositories()
    }

    private func loadRepositories() {
        view?.isLoading(isLoading: true)
        repositoriesInteractor.getRepositories(onResult: onRepositoriesLoaded, onError: onRepositoriesFailed)
    }

    private func onRepositoriesLoaded(repositories: [Repository]) {
        view?.isLoading(isLoading: false)
        view?.showRepositories(data: repositories)
    }

    private func onRepositoriesFailed(throwable: KotlinThrowable) {
        view?.isLoading(isLoading: false)
        view?.isError(isError: true, message: throwable.message)
    }
}
