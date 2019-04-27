package com.adg.superherobucket.presentation

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<T, S : BaseViewModel<T>> : AppCompatActivity() {

    protected abstract val internalViewModel : S
    val viewModel : S get() = internalViewModel

    val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayoutId())

        setupView()

        viewModel.viewState.observe(this, Observer { manageViewState(it) })
    }

    override fun onDestroy() {
        compositeDisposable.dispose()

        super.onDestroy()
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun setupView()

    abstract fun manageViewState(viewState: T?)


}