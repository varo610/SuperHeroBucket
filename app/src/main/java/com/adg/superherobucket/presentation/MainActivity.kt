package com.adg.superherobucket.presentation

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.transition.TransitionManager
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import com.adg.superherobucket.R
import com.adg.superherobucket.presentation.model.MainViewState
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private val searchHiddenCS = ConstraintSet()
    private val searchVisibleCS = ConstraintSet()
    private var searchHidden = true

    private val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()

        viewModel.mainViewState.observe(this, Observer { manageViewState(it) })

        viewModel.search()

    }


    private fun setupView() {

        setSupportActionBar(toolbar)

        searchHiddenCS.clone(constraintLayout)
        searchVisibleCS.clone(this, R.layout.activity_main_alt)

        superHeroesRV.layoutManager = LinearLayoutManager(this)
        superHeroesRV.adapter = adapter

    }

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

    private fun manageViewState(mainViewState: MainViewState?) {

        mainViewState?.let {
            it.superHeroList.let { list ->


                emptyListIV.visibility = if(list.isEmpty()) VISIBLE else GONE
                emptyListTV.visibility = if(list.isEmpty()) VISIBLE else GONE
                superHeroesRV.visibility = if(list.isNotEmpty()) VISIBLE else GONE

                adapter.submitList(list)

            }
        }

    }

}
