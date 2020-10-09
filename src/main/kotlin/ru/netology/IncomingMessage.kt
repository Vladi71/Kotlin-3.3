package ru.netology


data class IncomingMessage(
    val author: String,
    val authorId: Int,
    val messageId: Int,
    var text: String,
    var readStatus: Boolean = false
)
