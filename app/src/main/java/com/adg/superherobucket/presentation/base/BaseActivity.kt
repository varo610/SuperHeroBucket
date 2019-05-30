package com.adg.superherobucket.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.adg.superherobucket.presentation.model.BaseViewState
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<T, S : BaseViewModel<T>> : AppCompatActivity() {

    protected abstract val internalViewModel : S
    val viewModel : S get() = internalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayoutId())

        setupView()

        viewModel.viewState.observe(this, Observer { manageViewState(it) })
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun setupView()

    abstract fun manageViewState(viewState: BaseViewState<T>?)


}