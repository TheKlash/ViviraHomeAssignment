package de.alekseipopov.vivirahomeassignment.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.alekseipopov.vivirahomeassignment.domian.SearchRepositoryUseCase
import de.alekseipopov.vivirahomeassignment.ui.model.SearchResponseItem
import de.alekseipopov.vivirahomeassignment.utils.Converters
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val RESPONSE_PAGE_SIZE = 30

@HiltViewModel
class SearchViewModel @Inject constructor(
    val useCase: SearchRepositoryUseCase
): ViewModel() {

    private var CURRENT_PAGE_COUNTER = 1

    private val _searchResults = MutableLiveData<List<SearchResponseItem>>()
    val searchResults : LiveData<List<SearchResponseItem>>
        get() = _searchResults

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading : LiveData<Boolean>
        get() = _showLoading

    private val _showPrevButton = MutableLiveData(false)
    val showPrevButton : LiveData<Boolean>
        get() = _showPrevButton

    private val _showNextButton = MutableLiveData(false)
    val showNextButton : LiveData<Boolean>
        get() = _showNextButton

    private val _pageCounter = MutableLiveData<String?>(null)
    val pageCounter : LiveData<String?>
        get() = _pageCounter

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage : LiveData<String?>
        get() = _errorMessage



    fun resetCounter() {
        CURRENT_PAGE_COUNTER = 1
    }

    fun searchNextPage(query: String?) {
        CURRENT_PAGE_COUNTER++
        search(query)
    }

    fun searchPreviousPage(query: String?) {
        CURRENT_PAGE_COUNTER--
        search(query)

    }

    fun search(query: String?, nextPage: Boolean = false, prevPage: Boolean = false) {
        val errorHandler = CoroutineExceptionHandler { _, throwable ->
            _errorMessage.value = "Error while making a request"
            _showLoading.value = false
            if (nextPage)
                CURRENT_PAGE_COUNTER--
            if (prevPage)
                CURRENT_PAGE_COUNTER++

        }
        viewModelScope.launch(errorHandler) {
            query?.let {
                _showLoading.value = true
                val response = useCase.search(it, CURRENT_PAGE_COUNTER, RESPONSE_PAGE_SIZE)
                val items = withContext(Dispatchers.Default) {
                    response.repos?.map { repo -> Converters.searchResponseModel_to_UiModel(repo) }
                } ?: emptyList<SearchResponseItem>()
                _showLoading.value = false
                _pageCounter.value = CURRENT_PAGE_COUNTER.toString()
                _showPrevButton.value = CURRENT_PAGE_COUNTER > 1
                _showNextButton.value = ((CURRENT_PAGE_COUNTER + 1) * CURRENT_PAGE_COUNTER) < (response.totalCount ?: 0L)
                _searchResults.value = items
            }
        }
    }
}