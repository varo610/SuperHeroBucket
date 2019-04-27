package com.adg.superherobucket.presentation

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.transition.TransitionManager
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import com.adg.superherobucket.R
import com.adg.superherobucket.presentation.model.MainViewState
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private val compositeDisposable = CompositeDisposable()

    private val searchHiddenCS = ConstraintSet()
    private val searchVisibleCS = ConstraintSet()
    private var searchHidden = true

    private val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()

        viewModel.mainViewState.observe(this, Observer { manageViewState(it) })

    }

    override fun onDestroy() {

        compositeDisposable.dispose()

        super.onDestroy()
    }

    private fun setupView() {

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


                emptyListIV.visibility = if (list.isEmpty()) VISIBLE else GONE
                emptyListTV.visibility = if (list.isEmpty()) VISIBLE else GONE
                superHeroesRV.visibility = if (list.isNotEmpty()) VISIBLE else GONE

                adapter.submitList(list)

            }
        }

    }

}
