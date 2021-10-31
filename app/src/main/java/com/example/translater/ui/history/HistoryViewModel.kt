package com.example.translater.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.translater.App
import com.example.translater.model.DataHistory
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HistoryViewModel : ViewModel() {

    private val _history = MutableLiveData<List<DataHistory>>().apply {
        runBlocking {
            launch {
                postValue(App.db.dataHistoryDao().getAll())
            }
        }
    }

    val history: LiveData<List<DataHistory>> = _history
}