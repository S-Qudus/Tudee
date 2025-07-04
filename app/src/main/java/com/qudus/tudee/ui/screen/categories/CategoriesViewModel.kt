package com.qudus.tudee.ui.screen.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qudus.tudee.R
import com.qudus.tudee.domain.entity.Category
import com.qudus.tudee.domain.service.CategoryService
import com.qudus.tudee.domain.service.TaskService
import com.qudus.tudee.ui.mapper.toCategoryUiItem
import com.qudus.tudee.ui.screen.editCategoryScreen.EditCategoryUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val categoryService: CategoryService,
    private val taskService: TaskService
) : ViewModel() {

    private val _uiState = MutableStateFlow(CategoriesUiState())
    val uiState = _uiState.asStateFlow()

    private val _createCategoryUiState = MutableStateFlow(CreateCategoryUiState())
    val createCategoryUiState = _createCategoryUiState.asStateFlow()

    private val _editCategoryUiState = MutableStateFlow(EditCategoryUiState())
    val editCategoryUiState = _editCategoryUiState.asStateFlow()

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
                            errorMessage = (throwable.message
                                ?: R.string.failed_to_load_categories).toString()
                        )
                    }
                }
                .collect { categories ->
                    val updatedCategories = mutableListOf<CategoryUiItem>()

                    categories.forEach { category ->
                        val tasks = taskService.getTasksByCategoryId(category.id).first()
                        updatedCategories.add(
                            category.toCategoryUiItem(
                                categoryId = category.id,
                                taskCount = tasks.size
                            )
                        )
                    }

                    _uiState.update {
                        it.copy(
                            categories = updatedCategories,
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                }
        }
    }

    fun createCategory(category: Category) {
        viewModelScope.launch {
            _createCategoryUiState.value = _createCategoryUiState.value.copy(isLoading = true)
            try {
                categoryService.createCategory(category)
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

    fun resetMessage() {
        _uiState.update { it.copy(createSuccessMessage = null) }
    }

    fun setShowBottomSheet(show: Boolean) {
        _uiState.update { it.copy(showBottomSheet = show) }
    }
}