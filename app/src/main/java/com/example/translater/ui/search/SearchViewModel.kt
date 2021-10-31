package com.example.translater.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.translater.App
import com.example.translater.HtmlManager
import com.example.translater.Language
import com.example.translater.RequestSearch
import com.example.translater.model.DataHistory
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SearchViewModel : ViewModel() {

    private val htmlManager = HtmlManager{
        if (it.result.size == 0)
            it.result.add("Not found")
        else
            saveResult(it)
        _result.postValue(it.result)
    }

    private val _result = MutableLiveData<ArrayList<String>>()
    val result: LiveData<ArrayList<String>> = _result

    fun search(value: String, from: Language, to: Language) {
        htmlManager.getTranslateValue(RequestSearch(value, from, to))
    }

    private fun saveResult(result: RequestSearch) = runBlocking {
        launch {
            val history = App.db.dataHistoryDao().getAll()
            if (history.size == 5)
                App.db.dataHistoryDao().delete(history[0])
            App.db.dataHistoryDao().insert(
                DataHistory(
                    result.value,
                    result.from,
                    result.to,
                    result.result.toString(),
                    System.currentTimeMillis()
                )
            )
        }
    }
}