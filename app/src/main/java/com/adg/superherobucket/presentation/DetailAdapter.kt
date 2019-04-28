package com.adg.superherobucket.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adg.superherobucket.R
import com.adg.superherobucket.presentation.model.DetailType
import com.adg.superherobucket.presentation.model.SuperHeroDetailItem
import kotlinx.android.synthetic.main.item_list_detail.view.*


class DetailAdapter : ListAdapter<SuperHeroDetailItem, DetailAdapter.DetailViewHolder>(SuperHeroDetailItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder =
        DetailViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_detail, parent, false)
        )

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: SuperHeroDetailItem) {
             when(item.type) {
                 DetailType.SP_NAME -> {itemView.contentIV.setImageResource(R.drawable.ic_name)}
                 DetailType.SP_FULLNAME -> {itemView.contentIV.setImageResource(R.drawable.ic_fullname)}
                 DetailType.SP_PLACE_OF_BIRTH -> {itemView.contentIV.setImageResource(R.drawable.ic_home)}
                 DetailType.SP_GENDER -> {itemView.contentIV.setImageResource(R.drawable.ic_gender)}
                 DetailType.SP_WEIGHT -> {itemView.contentIV.setImageResource(R.drawable.ic_weight)}
                 DetailType.SP_HEIGHT -> {itemView.contentIV.setImageResource(R.drawable.ic_height)}
             }

            itemView.contentTV.text = item.content
        }
    }
}

private class SuperHeroDetailItemDiffCallback : DiffUtil.ItemCallback<SuperHeroDetailItem>() {
    override fun areItemsTheSame(oldItem: SuperHeroDetailItem, newItem: SuperHeroDetailItem): Boolean =
        oldItem.type == newItem.type

    override fun areContentsTheSame(oldItem: SuperHeroDetailItem, newItem: SuperHeroDetailItem): Boolean =
        oldItem == newItem
}
