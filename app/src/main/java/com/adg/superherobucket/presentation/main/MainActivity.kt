package com.adg.superherobucket.presentation.main

import android.content.Intent
import android.transition.TransitionManager
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.adg.superherobucket.R
import com.adg.superherobucket.presentation.detail.DetailActivity
import com.adg.superherobucket.presentation.base.BaseActivity
import com.adg.superherobucket.presentation.model.MainViewState
import com.adg.superherobucket.presentation.model.SuperHero
import com.jakewharton.rxbinding3.widget.textChanges
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit


class MainActivity : BaseActivity<MainViewState, MainViewModel>() {

    private val searchHiddenCS = ConstraintSet()
    private val searchVisibleCS = ConstraintSet()
    private var searchHidden = true

    private val adapter =
        MainAdapter(itemClick = { viewModel.superHeroOnClick(it) })

    //region [BaseActivityImp]

    override val internalViewModel by viewModel<MainViewModel>()

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun setupView() {

        setSupportActionBar(toolbar)

        searchHiddenCS.clone(constraintLayout)
        searchVisibleCS.clone(this, R.layout.activity_main_alt)

        compositeDisposable.add(searchET.textChanges()
            .debounce(100, TimeUnit.MILLISECONDS)
            .subscribe {
                viewModel.search(it.toString())
            }
        )

        superHeroesRV.layoutManager = LinearLayoutManager(this)
        superHeroesRV.adapter = adapter

        viewModel.navigateToDetails.observe(this, Observer { gotToDetail(it) })

    }

    override fun manageViewState(viewState: MainViewState?) {

        viewState?.let {state ->

            state.superHeroList.let { list ->

                emptyListIV.visibility = if (list.isEmpty()) VISIBLE else GONE
                emptyListTV.visibility = if (list.isEmpty()) VISIBLE else GONE
                superHeroesRV.visibility = if (list.isNotEmpty()) VISIBLE else GONE

                adapter.submitList(list)

            }

        }

    }

    private fun gotToDetail(superHero: SuperHero?){
        superHero?.let {
            val intent = Intent(applicationContext, DetailActivity::class.java)
            intent.putExtra(DetailActivity.SUPER_HERO_EXTRA,it)
            startActivity(intent)
        }
    }

    //endregion [BaseActivityImp]

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.action_search -> {
                searchHidden = !searchHidden
                TransitionManager.beginDelayedTransition(constraintLayout)
                val constraint = if (searchHidden) searchHiddenCS else searchVisibleCS
                constraint.applyTo(constraintLayout)
            }
        }

        return super.onOptionsItemSelected(item)
    }

}
