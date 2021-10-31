package com.example.translater.ui.search

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import com.example.translater.Language

class SpinnerAdapter(private val context: Context,
                     private val callback: (Boolean, SpinnerAdapter) -> Unit
) {
    private val languages: HashMap<String, Language> = hashMapOf(
        Pair("rus", Language.RUSSIA),
        Pair("eng", Language.ENGLISH),
        Pair("fre", Language.FRENCH),
        Pair("ger", Language.GERMAN),
        Pair("ita", Language.ITALIAN)
    )

    private val itemSpinnerFrom = ArrayList<String>().apply {
        languages.forEach { v ->
            add(v.key)
        }
        callback.invoke(true, ArrayAdapter(
            context,
            android.R.layout.simple_spinner_item,
            this
        ))
    }

    private var itemSpinnerTo = ArrayList<String>().apply {
        add(" ")
    }


    fun selectLanguage(select: Int, from: Boolean) {
        var position: String = if (from)
            itemSpinnerFrom[select]
        else
            itemSpinnerTo[select]
        itemSpinnerTo = ArrayList()
        val language = languages[position]
        languages.forEach {
            if (language == Language.RUSSIA) {
                if (it.value != Language.RUSSIA)
                    itemSpinnerTo.add(it.key)
            }
            else
                if (it.value == Language.RUSSIA)
                    itemSpinnerTo.add(it.key)
        }
        callback.invoke(false, ArrayAdapter(
            context,
            android.R.layout.simple_spinner_item,
            itemSpinnerTo
        ))
    }

    fun getLanguageFrom(position: Int): Language {
        languages.forEach {
            if (itemSpinnerFrom[position] == it.key)
                return it.value
        }
        return Language.RUSSIA
    }

    fun getLanguageTo(position: Int): Language {
        languages.forEach {
            if (itemSpinnerTo[position] == it.key)
                return it.value
        }
        return Language.RUSSIA
    }

}