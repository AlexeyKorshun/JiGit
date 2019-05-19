/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.mpp.jigitbl.data.repositories

import com.rosberry.mpp.delight.SqlDriverFactory
import com.rosberry.mpp.jigitbl.JigitDatabase
import com.rosberry.mpp.jigitbl.OwnerQueries
import com.rosberry.mpp.jigitbl.RepositoryQueries
import com.rosberry.mpp.jigitbl.data.DatabaseConst
import com.rosberry.mpp.jigitbl.entity.Repository
import com.rosberry.mpp.jigitbl.entity.User
import com.squareup.sqldelight.db.SqlDriver

/**
 * @author Alexei Korshun on 07/03/2019.
 */
class RepositoryDb(
        sqlDriverFactory: SqlDriverFactory
) {

    private val sqlDriver: SqlDriver = sqlDriverFactory.create(DatabaseConst.DATABASE_NAME)
    private val database: JigitDatabase = JigitDatabase(sqlDriver)
    private val repositoryQueries: RepositoryQueries = database.repositoryQueries
    private val ownerQueries: OwnerQueries = database.ownerQueries

    fun getAllRepositories(): List<Repository> {
        return repositoryQueries.selectAll()
            .executeAsList()
            .asSequence()
            .map { repository ->
                val owner = ownerQueries.selectById(repository.owner_id)
                    .executeAsOne()
                Repository(repository.id, repository.name, User(owner.login, owner.id))
            }
            .toList()
    }

    fun saveRepositories(repos: List<Repository>) {
        repositoryQueries.clear()
        repos.asSequence()
            .forEach {
                ownerQueries.insertOwner(it.owner.id, it.owner.login)
                repositoryQueries.insertRepository(it.id, it.name, it.owner.id)
            }
    }
}