package com.example.chiandroidinternship.ui

import androidx.lifecycle.*
import com.example.chiandroidinternship.data.database.repo.FavouriteShibeDatabaseRepo
import com.example.chiandroidinternship.data.model.Shibe
import com.example.chiandroidinternship.data.repo.ShibeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val shibeRepository: ShibeRepository,
    private val favouriteShibeRepository: FavouriteShibeDatabaseRepo
) : ViewModel() {

    private val _shibes = MutableLiveData<List<Shibe>>()
    val shibes: LiveData<List<Shibe>>
        get() = _shibes

    private val _favouriteShibes = MutableStateFlow(emptyList<Shibe>())
    val favouriteShibes: StateFlow<List<Shibe>>
        get() = _favouriteShibes

    init {
        getInitialShibes()
        getFavouriteShibes()
    }

    private fun getInitialShibes() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val shibes = shibeRepository.getShibes()
                withContext(Dispatchers.Main) {
                    _shibes.value = shibes
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    throw e
                }
            }
        }
    }

    private fun getFavouriteShibes() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = favouriteShibeRepository.getAllShibes().distinctUntilChanged()
            withContext(Dispatchers.Main) {
                result.collect {
                    _favouriteShibes.value = it
                }
            }
        }
    }

    private fun addFavourite(shibe: Shibe) {
        viewModelScope.launch(Dispatchers.IO) {
            favouriteShibeRepository.insert(shibe)
        }
    }

    fun getNextShibes() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val shibes = shibeRepository.getShibes()
                withContext(Dispatchers.Main) {
                    _shibes.value = _shibes.value.run {
                        this?.toMutableList()?.apply {
                            addAll(shibes)
                        }?.toList()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    throw e
                }
            }
        }
    }

    fun changeFavourite(position: Int) {
        _shibes.value = _shibes.value?.apply {
            this[position].copy(isFavourite = !this[position].isFavourite)
        }
        val item = _shibes.value?.get(position)
        item?.let {
            if (it.isFavourite) {
                addFavourite(it)
            } else {
                val itemToDelete = _favouriteShibes.value.find { item.id == it.id }
                itemToDelete?.let { deleteFavourite(itemToDelete.id) }
            }
        }
    }

    fun deleteFavourite(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            favouriteShibeRepository.delete(id)
        }
    }
}