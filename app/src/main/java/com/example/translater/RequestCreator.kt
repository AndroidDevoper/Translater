package com.example.translater

class RequestCreator() {

    private val main = "https://www.multitran.com/m.exe?"

    fun russianFromEnglish(value: String) = main + "s=" + value + "&l1=2&l2=1"

    fun russianFromFrench(value: String) = main + "s=" + value + "&l1=2&l2=4"

    fun russianFromGerman(value: String) = main + "s=" + value + "&l1=2&l2=3"

    fun russianFromItalian(value: String) = main + "s=" + value + "&l1=2&l2=23"

    fun englishFromRussian(value: String) = main + "l1=1&l2=2&s=" + value

    fun frenchFromRussian(value: String) = main + "l1=4&l2=2&CL=1&s=" + value

    fun germanFromRussian(value: String) = main + "l1=3&l2=2&CL=1&s=" + value

    fun italianFromRussian(value: String) = main + "l1=23&l2=2&CL=1&s=" + value

}