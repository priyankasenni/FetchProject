package com.example.fetchproject.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchproject.data.model.Item
import com.example.fetchproject.data.repository.ItemRepository
import kotlinx.coroutines.launch

class ItemViewModel : ViewModel() {
    private val itemRepository = ItemRepository()
    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> = _items

    fun fetchItems() {
        viewModelScope.launch {
            try {
                val itemList = itemRepository.getItems()
                    .filter { it.name?.isNotBlank() == true }
                    .sortedWith(compareBy({ it.listId }, { it.name }))
                _items.value = itemList
            } catch (e: Exception) {
                // Handle error
                Log.e("ItemViewModel", "Error fetching items: ${e.message}")
            }
        }
    }
}
