package com.adg.superherobucket.presentation.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.adg.superherobucket.domain.SearchSuperHeroUseCase
import com.adg.superherobucket.presentation.RxImmediateSchedulerRule
import com.adg.superherobucket.presentation.model.SuperHero
import io.reactivex.Maybe
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit

class MainViewModelTest {

    private lateinit var viewModel:MainViewModel

    @Mock
    private lateinit var searchSuperHeroUseCase: SearchSuperHeroUseCase

    @Mock
    private lateinit var throwable: Throwable

    private val superHeroList: List<SuperHero> = listOf(superHero)

    @Rule
    @JvmField
    var mockitoRule = MockitoJUnit.rule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    private fun <T> uninitialized(): T = null as T

    @Before
    fun beforeTest() {
        viewModel = MainViewModel(searchSuperHeroUseCase)
    }

    @Test
    fun `search superhero OK`(){

        `when`(searchSuperHeroUseCase.searchSuperHero(any())).thenReturn(Maybe.just(superHeroList))

        //When
        viewModel.search("")

        //Then
        verify(searchSuperHeroUseCase).searchSuperHero(any())
        assert(superHeroList == viewModel.viewState.value?.superHeroList)

    }

    @Test
    fun `search superhero KO`(){

        `when`(searchSuperHeroUseCase.searchSuperHero(any())).thenReturn(Maybe.error(throwable))

        //When
        viewModel.search("")

        //Then
        verify(searchSuperHeroUseCase).searchSuperHero(any())
        assert(viewModel.viewState.value == null)

    }
}