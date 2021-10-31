package com.example.translater

data class RequestSearch (val value: String,
                          val from: Language,
                          val to: Language) {
    var result = ArrayList<String>()
}