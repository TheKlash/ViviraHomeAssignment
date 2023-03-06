package de.alekseipopov.vivirahomeassignment.ui.model

data class SearchResponseItem(
    val id: Long,
    val username: String? = null,
    val userAvatar: String? = null,
    val repoName: String? = null,
    val repoTitle: String? = null,
    val repoDesc: String? = null,
    val repoUrl: String? = null,
)