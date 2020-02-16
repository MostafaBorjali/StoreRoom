package com.okala.remote


import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit



object ServiceGenerator {


    private val httpClient = OkHttpClient.Builder()
    private var retrofit: Retrofit? = null


    fun <S> createService(serviceClass: Class<S>): S {
        if (retrofit == null) {
            val builder = Retrofit.Builder()
                .baseUrl("https://api.github.com/")

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            httpClient.addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("Authorization", "Bearer ")
                    .method(original.method(), original.body())
                val request = requestBuilder.build()
                val originalResponse = chain.proceed(request)
                originalResponse.newBuilder()
                    .build()


            }

            httpClient.addInterceptor(logging)
            httpClient.addNetworkInterceptor(StethoInterceptor())

            httpClient.connectTimeout(40, TimeUnit.SECONDS)
                .readTimeout(40, TimeUnit.SECONDS)
                .writeTimeout(40, TimeUnit.SECONDS)

            val client = httpClient.build()

            retrofit =
                builder.client(client).addConverterFactory(GsonConverterFactory.create()).build()
            return retrofit!!.create(serviceClass)
        } else {
            return retrofit!!.create(serviceClass)

        }
    }

}


