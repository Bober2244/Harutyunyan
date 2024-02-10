package com.example.tinkofflabproject.di

import com.example.tinkofflabproject.net.MovieApi
import com.example.tinkofflabproject.utils.API_KEY
import com.example.tinkofflabproject.utils.BASE_URL
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale

val apiModule = module {
    single { Cache(androidApplication().cacheDir, 10L * 1024 * 1024) }
    single { GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create() }
    single { get<Retrofit>().create(MovieApi::class.java) }

    single {
        OkHttpClient.Builder().cache(get()).apply {
            interceptors().add{
                val request = it.request()
                val url = request.url()
                    .newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .addQueryParameter("language", Locale.getDefault().toLanguageTag())
                    .build()
                it.proceed(request.newBuilder().url(url).build())
            }
        }.build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .build()
    }
}