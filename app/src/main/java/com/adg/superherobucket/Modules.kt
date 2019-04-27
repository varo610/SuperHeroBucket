package com.adg.superherobucket

import com.adg.superherobucket.data.ApiKeys
import com.adg.superherobucket.data.Repository
import com.adg.superherobucket.data.network.NetworkDatasource
import com.adg.superherobucket.data.network.NetworkDatasourceImp
import com.adg.superherobucket.data.network.SuperHeroApiService
import com.adg.superherobucket.domain.SearchSuperHeroUseCase
import com.adg.superherobucket.presentation.DetailViewModel
import com.adg.superherobucket.presentation.MainViewModel
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


private const val TIMEOUT: Long = 60
private const val BASE_URL: String = "https://superheroapi.com/api/${ApiKeys.superHeroApiKey}/"

val viewModelModule: Module = module {
    viewModel { MainViewModel(searchSuperHeroUseCase = get()) }
    viewModel { DetailViewModel() }
}

val domainModule: Module = module {
    single { SearchSuperHeroUseCase(repository = get()) }
}

val dataModule: Module = module {
    single { Repository(networkDatasource = get()) }
    single { NetworkDatasourceImp(superHeroApiService = get()) as NetworkDatasource }
}

val networkModule: Module = module {
    single {

        val okHttpClient = OkHttpClient.Builder()
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addNetworkInterceptor(StethoInterceptor())
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(SuperHeroApiService::class.java)
    }
}

