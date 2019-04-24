package com.adg.superherobucket.presentation

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adg.superherobucket.R
import com.adg.superherobucket.domain.model.SuperHero
import kotlinx.android.synthetic.main.item_list_super_hero.view.*

class MainAdapter : ListAdapter<SuperHero, MainAdapter.MainViewHolder>(CommentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
        MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_super_hero, parent, false)
        )

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: SuperHero) {
            itemView.nameTV.text = item.name
        }
    }
}

private class CommentDiffCallback : DiffUtil.ItemCallback<SuperHero>() {
    override fun areItemsTheSame(oldItem: SuperHero, newItem: SuperHero): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: SuperHero, newItem: SuperHero): Boolean =
        oldItem == newItem
}
