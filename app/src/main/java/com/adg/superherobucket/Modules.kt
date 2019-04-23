package com.adg.superherobucket

import com.adg.superherobucket.presentation.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module


fun initInjector() = loadModules

private val loadModules by lazy {
    loadKoinModules(
        viewModelModule
    )
}

val viewModelModule: Module = module {
    viewModel { MainViewModel() }
}

