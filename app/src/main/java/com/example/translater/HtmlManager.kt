package com.example.translater

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class HtmlManager(private val callback: (RequestSearch) -> Unit) {

    fun getTranslateValue(request: RequestSearch) = runBlocking {
        var url: String? = null
        if (request.from == Language.RUSSIA) {
            when(request.to) {
                Language.ENGLISH -> url = RequestCreator().russianFromEnglish(request.value)
                Language.FRENCH -> url = RequestCreator().russianFromFrench(request.value)
                Language.GERMAN -> url = RequestCreator().russianFromGerman(request.value)
                Language.ITALIAN -> url = RequestCreator().russianFromItalian(request.value)
            }
        }
        else {
            when(request.from) {
                Language.ENGLISH -> url = RequestCreator().englishFromRussian(request.value)
                Language.FRENCH -> url = RequestCreator().frenchFromRussian(request.value)
                Language.GERMAN -> url = RequestCreator().germanFromRussian(request.value)
                Language.ITALIAN -> url = RequestCreator().italianFromRussian(request.value)
            }
        }
        launch {
            withContext(Dispatchers.IO) {
                parse(request, Jsoup.connect(url).get())
            }
        }
    }

    private fun parse(request: RequestSearch, doc: Document?) {
        val list = ArrayList<String>()
        val body = doc!!.body()
            .child(2)
            .child(6)
            .child(3)
        if (body.childrenSize() != 0) {
            val values = body
                    .child(0)
                    .child(2)
                    .child(1)
            for(i in 0 until values.childrenSize()) {
                if (values.child(i).attr("href").isNotEmpty())
                    list.add(values.child(i).html())
            }
        }
        request.result = list
        callback.invoke(request)
    }
}