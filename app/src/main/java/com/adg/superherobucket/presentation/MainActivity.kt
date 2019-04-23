package com.adg.superherobucket.presentation

import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionManager
import android.view.Menu
import android.view.MenuItem
import com.adg.superherobucket.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModel()

    private val searchHiddenCS = ConstraintSet()
    private val searchVisibleCS = ConstraintSet()
    private var searchHidden = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setupView()

    }

    private fun setupView() {
        setSupportActionBar(toolbar)

        searchHiddenCS.clone(constraintLayout)
        searchVisibleCS.clone(this, R.layout.activity_main_alt)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId){
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
