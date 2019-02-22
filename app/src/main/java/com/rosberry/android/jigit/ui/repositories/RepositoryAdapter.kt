/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.jigit.ui.repositories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rosberry.android.jigit.R
import com.rosberry.mpp.jigitbl.entity.Repository

/**
 * @author Alexei Korshun on 22/02/2019.
 */
class RepositoryAdapter(
        private val repositories: List<Repository>
) : RecyclerView.Adapter<RepositoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repository, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun getItemCount(): Int = repositories.size

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) = holder.bind(repositories[position])
}