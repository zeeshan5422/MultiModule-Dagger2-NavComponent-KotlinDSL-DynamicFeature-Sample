package com.nfs.ascent.core.di.module

import com.google.gson.Gson
import com.nfs.ascent.core.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        private const val CLIENT_TIME_OUT = 120L
    }

    @Singleton
    @Provides
    fun provideGson() = Gson()

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
        }


    @Singleton
    @Provides
    fun provideGsonConvertorFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)

    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
//        chuckInterceptor: ChuckerInterceptor
    ): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder
            .addInterceptor(httpLoggingInterceptor)
//            .addInterceptor(chuckInterceptor)
            .connectTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        return okHttpBuilder.build()
    }

//    @Singleton
//    @Provides
//    fun provideCoreApi(
//        okHttpClient: OkHttpClient,
//        gsonConverterFactory: GsonConverterFactory
//    ): CoreApiInterface {
//        val retrofitBuilder = Retrofit.Builder()
//            .baseUrl(CoreApiInterface.BASE_URL)
//            .client(okHttpClient)
//            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//            .addConverterFactory(gsonConverterFactory)
//        return retrofitBuilder.build().create(CoreApiInterface::class.java)
//    }

//    @Singleton
//    @Provides
//    fun provideChuckerInterceptor(@ApplicationContext context: Context) =
//        ChuckerInterceptor(context = context)


}