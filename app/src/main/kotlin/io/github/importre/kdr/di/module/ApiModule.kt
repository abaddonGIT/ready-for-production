package io.github.importre.kdr.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import io.github.importre.kdr.BuildConfig
import io.github.importre.kdr.api.Github
import io.github.importre.kdr.intf.IScheduler
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.lang.reflect.Type
import javax.inject.Singleton

@Module
class ApiModule(val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context = context

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder()
            .addInterceptor(initHttpLoggingInterceptor())
            .addInterceptor(UrlInterceptor())
            .build()

    private fun initHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    @Singleton
    fun provideApiService(client: OkHttpClient): Github {
        val endpoint = "https://api.github.com/"
        val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(endpoint)
                .addCallAdapterFactory(SledgeCallAdapter.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(Github::class.java)
    }

    class UrlInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response? {
            val r = chain.request()
            val url = r.url().newBuilder()
                    .addQueryParameter("app", "android")
                    .build()
            val request = r.newBuilder()
                    .url(url)
                    .addHeader("Accept", "application/vnd.github.v3+json")
                    .addHeader("User-Agent", "kdr")
                    .addHeader("Authorization", "token ${BuildConfig.GITHUB_TOKEN}")
                    .build()
            return chain.proceed(request)
        }
    }

    class SledgeCallAdapter(val schedulers: IScheduler) : CallAdapter.Factory() {

        companion object {
            fun create(): SledgeCallAdapter {
                return SledgeCallAdapter(object : IScheduler {
                    override fun main(): Scheduler = AndroidSchedulers.mainThread()
                    override fun background(): Scheduler = Schedulers.newThread()
                })
            }
        }

        override fun get(returnType: Type,
                         annotations: Array<Annotation>,
                         retrofit: Retrofit): CallAdapter<Observable<*>>? {
            val rxFactory = RxJavaCallAdapterFactory.create()
            val adapter = rxFactory.get(returnType, annotations, retrofit)
            return ThreadCallAdapter(adapter as? CallAdapter<*>?, schedulers)
        }
    }

    class ThreadCallAdapter(val adapter: CallAdapter<*>?,
                            val schedulers: IScheduler) : CallAdapter<Observable<*>> {

        override fun <R : Any?> adapt(call: Call<R>?): Observable<*>? {
            if (adapter == null) return null
            val adapt = adapter.adapt(call)
            if (adapt is Observable<*>) {
                return adapt
                        .subscribeOn(schedulers.background())
                        .observeOn(schedulers.main())
            } else {
                return null
            }
        }

        override fun responseType(): Type? {
            return adapter?.responseType()
        }
    }
}
