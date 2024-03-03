package com.hcode.hgames.components

import org.w3c.xhr.XMLHttpRequest


fun readJsonFile(filePath: String, callback: (dynamic) -> Unit) {
    val xhr = XMLHttpRequest()
    xhr.open("GET", filePath)
    xhr.onload = {
        if (xhr.status == 200.toShort()) {
            val json = JSON.parse<dynamic>(xhr.responseText)
            callback(json)
        } else {
            console.error("Failed to load JSON file: ${xhr.status}")
        }
    }
    xhr.send()
}


fun readTextFile(filePath: String, callback: (String) -> Unit) {
    val xhr = XMLHttpRequest()
    xhr.open("GET", filePath)
    xhr.onload = {
        if (xhr.status == 200.toShort()) {
            val text = xhr.responseText
            callback(text)
        } else {
            console.error("Failed to load text file: ${xhr.status}")
        }
    }
    xhr.send()
}

