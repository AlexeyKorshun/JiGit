/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.jigit.ui.repositories

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rosberry.mpp.jigitbl.entity.Repository
import kotlinx.android.synthetic.main.item_repository.view.*

/**
 * @author Alexei Korshun on 22/02/2019.
 */
class RepositoryViewHolder(
        view: View
) : RecyclerView.ViewHolder(view) {

    fun bind(repository: Repository) {
        itemView.repositoryNameView.text = repository.name
    }
}