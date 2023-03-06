package de.alekseipopov.vivirahomeassignment.utils

import de.alekseipopov.vivirahomeassignment.domian.model.SearchResponseModel
import de.alekseipopov.vivirahomeassignment.network.model.GithubRepoResponse
import de.alekseipopov.vivirahomeassignment.network.model.GithubRepoResponseItem
import de.alekseipopov.vivirahomeassignment.ui.model.SearchResponseItem

object Converters {
    fun githubRepoResponse_to_SearchResponseModel(githubResponse: GithubRepoResponse): SearchResponseModel {

        val reposList = mutableListOf<SearchResponseModel.Repo>()

        githubResponse.items.forEach {
            val id = it.id

            val userInfo = SearchResponseModel.Repo.UserInfo()
            userInfo.name = it.owner.login
            userInfo.avatarUrl = it.owner.avatar_url

            val repoInfo = SearchResponseModel.Repo.RepoInfo()
            repoInfo.name = it.full_name
            repoInfo.title = it.name
            repoInfo.desc = it.description
            repoInfo.url = it.html_url

            val repo = SearchResponseModel.Repo()
            repo.repoInfo = repoInfo
            repo.userInfo = userInfo
            repo.id = id

            reposList.add(repo)
        }

        return SearchResponseModel(
            totalCount = githubResponse.total_count.toLong(),
            repos = reposList.toList()
        )
    }

    fun searchResponseModel_to_UiModel(repo: SearchResponseModel.Repo): SearchResponseItem {
        return SearchResponseItem(
            id = repo.id ?: 0L,
            username = repo.userInfo?.name,
            userAvatar = repo.userInfo?.avatarUrl,
            repoName = repo.repoInfo?.name,
            repoTitle = repo.repoInfo?.title,
            repoDesc = repo.repoInfo?.desc,
            repoUrl = repo.repoInfo?.url
        )
    }
}
