package com.adg.superherobucket

import androidx.room.Room
import com.adg.superherobucket.data.Repository
import com.adg.superherobucket.data.RepositoryImp
import com.adg.superherobucket.data.db.DBDatasource
import com.adg.superherobucket.data.db.DBDatasourceImp
import com.adg.superherobucket.data.db.SuperHeroDataBase
import com.adg.superherobucket.data.network.*
import com.adg.superherobucket.domain.AddFavoriteSuperHero
import com.adg.superherobucket.domain.GetFavoriteSuperHeros
import com.adg.superherobucket.domain.RemoveFavoriteSuperHero
import com.adg.superherobucket.domain.SearchSuperHeroUseCase
import com.adg.superherobucket.presentation.detail.DetailViewModel
import com.adg.superherobucket.presentation.main.MainViewModel
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
    viewModel {
        DetailViewModel(
            addFavoriteSuperHero = get(),
            removeFavoriteSuperHero = get()
        )
    }
}

val domainModule: Module = module {
    single { SearchSuperHeroUseCase(repository = get()) }
    single { GetFavoriteSuperHeros(repository = get()) }
    single { AddFavoriteSuperHero(repository = get()) }
    single { RemoveFavoriteSuperHero(repository = get()) }
}

val dataModule: Module = module {
    single {
        RepositoryImp(
            networkDatasource = get(),
            dbDatasource = get()
        ) as Repository
    }

    single { NetworkDatasourceImp(superHeroApiService = get()) as NetworkDatasource }
    single { DBDatasourceImp(superHeroDAO = get()) as DBDatasource }
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
            .addCallAdapterFactory(CustomCallAdapterFactory.create())
            .build()
            .create(SuperHeroApiService::class.java)
    }
}

val dbModule: Module = module {

    single {
        Room.databaseBuilder(
            get(),
            SuperHeroDataBase::class.java, "superhero.db"
        ).build()
    }

    single { get<SuperHeroDataBase>().superHeroDao() }

}

