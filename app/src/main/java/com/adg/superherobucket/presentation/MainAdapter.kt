package com.adg.superherobucket.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adg.superherobucket.R
import com.adg.superherobucket.presentation.model.SuperHero
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_list_super_hero.view.*

class MainAdapter constructor(
    private val itemClick: (String) -> Unit
) : ListAdapter<SuperHero, MainAdapter.MainViewHolder>(SuperHeroDiffCallback()) {

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
            itemView.fullnameTV.text = item.biography.fullName
            Glide
                .with(itemView.contentIV)
                .load(item.image.url)
                .apply(RequestOptions.circleCropTransform())
                .into(itemView.contentIV)
            itemView.setOnClickListener { itemClick.invoke(item.id) }
            if(item.favorite)
                itemView.favIV.visibility = VISIBLE
            else
                itemView.favIV.visibility = GONE

        }
    }
}

private class SuperHeroDiffCallback : DiffUtil.ItemCallback<SuperHero>() {
    override fun areItemsTheSame(oldItem: SuperHero, newItem: SuperHero): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: SuperHero, newItem: SuperHero): Boolean =
        oldItem == newItem
}
