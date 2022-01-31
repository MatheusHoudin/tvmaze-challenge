package com.matheus.tvmazechallenge.features.persondetails.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.matheus.tvmazechallenge.R
import com.matheus.tvmazechallenge.databinding.CastCreditShowLayoutBinding
import com.matheus.tvmazechallenge.features.persondetails.entity.CastCreditsEntity
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.shared.base.BaseAdapter
import com.matheus.tvmazechallenge.shared.base.BaseViewHolder

class CastCreditsShowAdapter : BaseAdapter<CastCreditsEntity>() {

    lateinit var seeMoreDetailsClickListener: (TVShowEntity) -> Unit
    lateinit var openTVShowOnWebClickListener: (String) -> Unit

    override fun getViewHolder(parent: ViewGroup) = CastCreditsViewHolder(
        CastCreditShowLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getLayoutRes() = R.layout.cast_credit_show_layout

    inner class CastCreditsViewHolder(private val binding: CastCreditShowLayoutBinding) :
        BaseViewHolder<CastCreditsEntity>(binding.root) {
        override fun bind(item: CastCreditsEntity, position: Int) {
            binding.apply {
                castCredit = item
                castCreditShowBtSeeMoreDetails.setOnClickListener {
                    seeMoreDetailsClickListener(item.tvShow)
                }
                castCreditShowBtOpenOnWeb.setOnClickListener {
                    openTVShowOnWebClickListener(item.tvShow.url)
                }
                executePendingBindings()
            }
        }
    }
}