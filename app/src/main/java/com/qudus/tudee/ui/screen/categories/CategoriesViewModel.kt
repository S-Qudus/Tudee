package com.qudus.tudee.ui.screen.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qudus.tudee.R
import com.qudus.tudee.domain.service.CategoryService
import com.qudus.tudee.domain.service.TaskService
import com.qudus.tudee.ui.mapper.toCategoryUiItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val categoryService: CategoryService,
    private val taskService: TaskService
) : ViewModel() {

    private val _uiState = MutableStateFlow(CategoriesUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadCategories()
    }

    fun loadCategories() {
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }

        viewModelScope.launch(Dispatchers.IO) {
            categoryService.getCategories()
                .catch { throwable ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = (throwable.message ?: R.string.failed_to_load_categories).toString()
                        )
                    }
                }
                .collect { categories ->
                    categories.map { category ->
                        taskService.getTasksByCategoryId(category.id).collect { tasks ->
                            _uiState.update {
                                it.copy(
                                    categories = categories.map {
                                        it.toCategoryUiItem(
                                            categoryId = category.id,
                                            taskCount = tasks.size
                                        )
                                    },
                                    isLoading = false,
                                    errorMessage = null
                                )
                            }
                        }
                    }
                }
        }
    }

    fun navigateToCategoryDetails(categoryId: Long) {
        // We need to add navigation impl to turn on Qamar screen
        TODO("Not yet implemented")
    }

    fun resetMessage() {
        _uiState.update { it.copy(createSuccessMessage = null) }
    }

    fun setShowBottomSheet(show: Boolean) {
        _uiState.update { it.copy(showBottomSheet = show) }
    }
}