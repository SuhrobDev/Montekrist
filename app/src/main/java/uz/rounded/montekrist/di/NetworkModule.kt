package uz.rounded.montekrist.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.rounded.montekrist.R
import uz.rounded.montekrist.data.remote.ApiService
import uz.rounded.montekrist.presentation.extensions.SharedPref
import uz.rounded.montekrist.presentation.extensions.getString
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGsonConvertorFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideSharedPref(
        @ApplicationContext context: Context
    ): SharedPref =
        SharedPref(context)

    @Singleton
    @Provides
    fun provideAppContext(@ApplicationContext context: Context) = context

    @Singleton
    @Provides
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
    ): OkHttpClient {
        val chuckInterceptor = ChuckerInterceptor.Builder(context).maxContentLength(500L)
            .alwaysReadResponseBody(true).build()
        val builder = OkHttpClient.Builder().addInterceptor(chuckInterceptor)
            .addNetworkInterceptor(Interceptor { chain: Interceptor.Chain ->
                val request =
                    chain.request().newBuilder().addHeader("Content-Type", "application/json")
                        .addHeader("Accept", "application/json").build()
                chain.proceed(request)
            }).build()
        return builder
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        gsonGsonConverterFactory: GsonConverterFactory, builder: OkHttpClient
    ): Retrofit = Retrofit.Builder().baseUrl(getString(R.string.base_url)).client(builder)
        .addConverterFactory(gsonGsonConverterFactory).build()


    @Singleton
    @Provides
    fun provideMainService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

}