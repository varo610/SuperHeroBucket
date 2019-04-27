package com.adg.superherobucket.presentation

import android.support.v7.widget.LinearLayoutManager
import com.adg.superherobucket.R
import com.adg.superherobucket.domain.model.toDetail
import com.adg.superherobucket.presentation.model.DetailViewState
import com.adg.superherobucket.presentation.model.SuperHero
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel


class DetailActivity : BaseActivity<DetailViewState,DetailViewModel>() {

    companion object {
        const val SUPER_HERO_EXTRA = "SUPER_HERO_EXTRA"
    }

    private val superHero by lazy { intent.getParcelableExtra<SuperHero>(SUPER_HERO_EXTRA) }
    private val adapter = DetailAdapter()

    //region [BaseActivityImp]

    override val internalViewModel by viewModel<DetailViewModel>()

    override fun getLayoutId(): Int = R.layout.activity_detail

    override fun setupView() {

        setSupportActionBar(toolbar)

        Glide
            .with(imageView)
            .load(superHero.image.url)
            .into(imageView)

        superHero.toDetail()


        detailsRV.layoutManager = LinearLayoutManager(this)
        detailsRV.adapter = adapter

        adapter.submitList(superHero.toDetail())

    }

    override fun manageViewState(viewState: DetailViewState?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //endregion [BaseActivityImp]

}
