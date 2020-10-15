package ru.netology

fun main() {
    val chatService = ChatService()

    chatService.addIncomingMessage(
            IncomingMessage(
                    author = "Юрий",
                    messageId = 12,
                    authorId = 12,
                    text = "Привет, как дела с дз с лямбдами?",
                    readStatus = false
            ))
    chatService.addOutgoingMessage(
            OutgoingMessage(
                    recipientId = 11,
                    messageId = 45,
                    text = "Привет, потихонечку делаю"
            )
    )
    chatService.editMessage(45, "Привет, застрял с ней, очень сложная задачка")

    chatService.addIncomingMessage(
            IncomingMessage(
                    author = "Юрий",
                    messageId = 13,
                    authorId = 12,
                    text = "Ну давай, крепись!",
                    readStatus = false
            ))

    chatService.addOutgoingMessage(
            OutgoingMessage(
                    recipientId = 11,
                    messageId = 77,
                    text = "Окей, спасибо!"
            ))

    chatService.addIncomingMessage(
            IncomingMessage(
                    author = "Анатолий",
                    messageId = 17,
                    authorId = 8,
                    text = "Когда уже сдащь все дз?!",
                    readStatus = false
            ))

    chatService.getMessagesFromChat(12, 13, 2)
    println(chatService.getMessagesFromChat(12, 12, 2))
    chatService.getUnreadChatsCount()
    println(chatService.getUnreadChatsCount())
    chatService.delChat(11)

}