package de.alekseipopov.vivirahomeassignment.domian

import de.alekseipopov.vivirahomeassignment.domian.model.SearchResponseModel

interface SearchRepositoryUseCase {

    suspend fun search(query: String?, page: Int, pageSize: Int = 1): SearchResponseModel

}