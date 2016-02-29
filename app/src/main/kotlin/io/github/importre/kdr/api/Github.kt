package io.github.importre.kdr.api

import io.github.importre.kdr.api.repo.Repository
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable
import java.util.*

interface Github {

    @GET("/users/{user}/repos")
    fun getRepos(@Path("user") user: String,
                 @Query("per_page") perPage: Int = 100): Observable<ArrayList<Repository>>
}
