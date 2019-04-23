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

    private val constraintSet1 = ConstraintSet()
    private val constraintSet2 = ConstraintSet()
    private var changed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setupView()

    }

    private fun setupView() {
        setSupportActionBar(toolbar)

        constraintSet1.clone(constraintLayout)
        constraintSet2.clone(this, R.layout.activity_main_alt)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId){
            R.id.action_search -> {
                TransitionManager.beginDelayedTransition(constraintLayout)
                val constraint = if (changed) constraintSet1 else constraintSet2
                constraint.applyTo(constraintLayout)
                changed = !changed
            }
        }

        return super.onOptionsItemSelected(item)
    }

}
