package com.example.translater

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect(): Unit = runBlocking {
        launch {
            HtmlManager{
                print(it)
            }.getTranslateValue("jsdfbbn", Language.ENGLISH, Language.RUSSIA)
        }
    }
}