package com.vini.feature_news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vini.core_data.domain.NewsUseCase
import com.vini.core_data.model.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase,
) : ViewModel() {
    val state = MutableStateFlow<NewsState>(NewsState.Loading)

    init {
        getLorNews()
    }

    fun getLorNews() {
        viewModelScope.launch(context = Dispatchers.IO) {
            val result = newsUseCase.invoke()
            viewModelScope.launch(context = Dispatchers.Main) {
                state.value = when (result) {
                    is ApiResult.Success -> {
                        if (result.data.isNullOrEmpty()) {
                            NewsState.Empty
                        } else {
                            NewsState.NewsList(news = result.data ?: emptyList())
                        }
                    }
                    is ApiResult.Error -> {
                        NewsState.Error(
                            error = result.message ?: "An unexpected error occured"
                        )
                    }
                    is ApiResult.Loading -> {
                        NewsState.Loading
                    }
                }
            }
        }
    }
}
