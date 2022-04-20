package me.thekusch.ozancaseproject.di

import me.thekusch.ozancaseproject.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val dataModule = module {
    single {
        provideRetrofit()
    }
}

private fun provideRetrofit(): Retrofit {
    val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    val requestInterceptor = Interceptor {
        var request = it.request()
        request = request.newBuilder()
            .addHeader("x-access-token", BuildConfig.API_KEY)
            .build()

        it.proceed(request)
    }

    val client =
        OkHttpClient.Builder().addInterceptor(loggingInterceptor).addInterceptor(requestInterceptor)
            .build()
    return Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)
        .baseUrl(BuildConfig.BASE_URL)
        .build()
}

