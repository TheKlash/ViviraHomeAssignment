package de.alekseipopov.vivirahomeassignment.domian.model

data class SearchResponseModel(
    val totalCount: Long? = null,
    val repos: List<Repo>? = null
) {

    class Repo() {
        var id: Long? = null
        var userInfo: UserInfo? = null
        var repoInfo: RepoInfo? = null

        class UserInfo() {
            var name: String? = null
            var avatarUrl: String? = null
        }

        class RepoInfo() {
            var name: String? = null
            var title: String? = null
            var desc: String? = null
            var url: String? = null
        }

    }

}
