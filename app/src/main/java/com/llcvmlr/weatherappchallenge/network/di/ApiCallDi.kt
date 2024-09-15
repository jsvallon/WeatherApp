package com.llcvmlr.weatherappchallenge.network.di


import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.llcvmlr.weatherappchallenge.network.ApiCallService
import com.llcvmlr.weatherappchallenge.network.NetWorkConstant
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiCallDi {

    @Provides
    @Singleton
    fun provideBaseUrl(): String {
        return NetWorkConstant.BASE_URL
    }

    @Provides
    @Singleton
    fun provideMoshi() : Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideAuthInterceptor(
        @ApplicationContext context: Context
    ): AuthInterceptor {
        return AuthInterceptor(context)
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(
        @ApplicationContext context: Context
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        baseUrl: String? = NetWorkConstant.BASE_URL,
        moshi: Moshi,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl?: NetWorkConstant.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiCallService(
        retrofit: Retrofit
    ): ApiCallService {
        return retrofit.create(ApiCallService::class.java)
    }
}