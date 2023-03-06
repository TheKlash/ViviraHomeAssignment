package de.alekseipopov.vivirahomeassignment.network

import de.alekseipopov.vivirahomeassignment.network.model.GithubRepoResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GithubRepoApi {
    @Headers("User-Agent: TheKlash")
    @GET("search/repositories")
    suspend fun getRepos(
        @Query("q") query: String?, @Query("per_page") perPage: Int, @Query("page") page: Int
    ): GithubRepoResponse
}