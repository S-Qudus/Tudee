package com.qudus.tudee.ui.screen.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qudus.tudee.domain.entity.Category
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
    private val repository: CategoryService,
    private val taskService: TaskService
) : ViewModel() {

    private val _uiState = MutableStateFlow(CategoriesUiState())
    val uiState = _uiState.asStateFlow()

    private val _createCategoryUiState = MutableStateFlow(CreateCategoryUiState())
    val createCategoryUiState = _createCategoryUiState.asStateFlow()

    init {
        loadCategories()
    }

    fun loadCategories() {
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }

        viewModelScope.launch(Dispatchers.IO) {
            repository.getCategories()
                .catch { throwable ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = throwable.message ?: "Failed to load categories"
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

    fun createCategory(category: Category) {
        viewModelScope.launch {
            _createCategoryUiState.value = _createCategoryUiState.value.copy(isLoading = true)
            try {
                repository.createCategory(category)
                _createCategoryUiState.value = _createCategoryUiState.value.copy(
                    isCreated = true,
                    isLoading = false
                )
                _uiState.value = _uiState.value.copy(
                    createSuccessMessage = "Category created successfully"
                )
                loadCategories()
            } catch (e: Exception) {
                _createCategoryUiState.value = _createCategoryUiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message
                )
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