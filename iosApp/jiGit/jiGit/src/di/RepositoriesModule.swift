//
// Created by Aleksei Korshun on 2019-02-22.
// Copyright (c) 2019 Aleksei Korshun. All rights reserved.
//

import FRAMEWORK

class RepositoriesModule {

    func provideViewController() -> RepositoriesViewController {
        let presenter: RepositoriesPresenter = providePresenter()
        let viewController = RepositoriesViewController(presenter)
        presenter.view = viewController
        return viewController
    }

    private func providePresenter() -> RepositoriesPresenter {
        return RepositoriesPresenter(provideRepositoriesInteractor())
    }

    private func provideRepositoriesInteractor() -> RepositoriesInteractor {
        return RepositoriesInteractor(repositoriesRepository: provideRepositoriesRepository())
    }

    private func provideRepositoriesRepository() -> RepositoriesRepository {
        return RepositoriesRepository(
            networkManagerFactory: NetworkManagerFactory(),
            repositoriesApi: provideRepositoriesApi(),
            repositoryDb: RepositoryDb(sqlDriverFactory: SqlDriverFactory())
        )
    }

    private func provideRepositoriesApi() -> RepositoryApi {
        return RepositoryApi(client: provideHttpClient(), authManager: provideAuthManager())
    }

    private func provideHttpClient() -> Ktor_client_coreHttpClient {
        return KtorFactory().createKtor()
    }

    private func provideAuthManager() -> AuthManager {
        return AuthManager(factory: PlatformPreferences.Factory())
    }
}
