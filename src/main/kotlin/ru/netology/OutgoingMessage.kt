package ru.netology

data class OutgoingMessage(
    val author: String = "Влад",
    val authorId: Int = 111,
    val recipientId: Int,
    val messageId: Int,
    var text: String,
    val readStatus: Boolean = true
)
