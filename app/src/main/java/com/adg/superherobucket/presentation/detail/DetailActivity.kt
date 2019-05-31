package com.adg.superherobucket.presentation.detail

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.adg.superherobucket.R
import com.adg.superherobucket.presentation.base.BaseActivity
import com.adg.superherobucket.presentation.model.BaseViewState
import com.adg.superherobucket.presentation.model.DetailViewState
import com.adg.superherobucket.presentation.model.State.*
import com.adg.superherobucket.presentation.model.SuperHero
import com.adg.superherobucket.presentation.model.toDetail
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel


class DetailActivity : BaseActivity<DetailViewState, DetailViewModel>() {

    companion object {
        const val SUPER_HERO_EXTRA = "SUPER_HERO_EXTRA"
    }

    private val superHero by lazy { intent.getParcelableExtra<SuperHero>(SUPER_HERO_EXTRA) }
    private val adapter = DetailAdapter()

    //region [BaseActivityImp]

    override val internalViewModel by viewModel<DetailViewModel>()

    override fun getLayoutId(): Int = R.layout.activity_detail

    override fun setupView() {

        viewModel.setSuperHero(superHero)

        setSupportActionBar(toolbar)

        detailsRV.layoutManager = LinearLayoutManager(this)
        detailsRV.adapter = adapter

        favFAB.setOnClickListener {
            viewModel.favButtonOnClick()
        }

    }

    override fun manageViewState(viewState: BaseViewState<DetailViewState>?) {
        viewState?.let {

            when(it.state){
                OK -> {
                    it.data?.superHero?.let { superHero ->
                        Glide
                            .with(imageView)
                            .load(superHero.image.url)
                            .into(imageView)

                        adapter.submitList(superHero.toDetail())

                        if (superHero.favorite)
                            favFAB.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_remove_favorite))
                        else
                            favFAB.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite))
                    }
                }
                LOADING -> TODO()
                ERROR -> TODO()
            }

        }
    }

    //endregion [BaseActivityImp]

}
