package de.alekseipopov.vivirahomeassignment.domian

import de.alekseipopov.vivirahomeassignment.domian.model.SearchResponseModel
import de.alekseipopov.vivirahomeassignment.network.GithubRepoApi
import de.alekseipopov.vivirahomeassignment.utils.Converters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchRepositoryUseCaseImpl @Inject constructor(
    private val api: GithubRepoApi
) : SearchRepositoryUseCase {
    override suspend fun search(
        query: String?, page: Int, pageSize: Int
    ): SearchResponseModel = withContext(Dispatchers.IO) {
        val githubRepoResponse = api.getRepos(query = query, page = page, perPage = pageSize)
        Converters.githubRepoResponse_to_SearchResponseModel(githubRepoResponse)
    }
}