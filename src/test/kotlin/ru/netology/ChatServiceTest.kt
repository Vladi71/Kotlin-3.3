package ru.netology

import org.junit.Test

import org.junit.Assert.*

class ChatServiceTest {

    @Test
    fun editMessageTrue() {
        val service = ChatService()
        service.addOutgoingMessage(OutgoingMessage(
                recipientId = 11,
                messageId = 45,
                text = "Привет, потихонечку делаю"
        ))
        val result = service.editMessage(45, "Привет, застрял с ней, очень сложная задачка")
        assertTrue(result)
    }

    @Test
    fun editMessageFalse() {
        val service = ChatService()
        service.addOutgoingMessage(OutgoingMessage(
                recipientId = 11,
                messageId = 45,
                text = "Привет, потихонечку делаю"
        ))
        val result = service.editMessage(77, "Привет, застрял с ней, очень сложная задачка")
        assertFalse(result)
    }

    @Test
    fun delChatTrue() {
        val service = ChatService()
        service.addOutgoingMessage(OutgoingMessage(
                recipientId = 11,
                messageId = 45,
                text = "Привет, потихонечку делаю"
        ))
        val result = service.delChat(11)
        assertTrue(result)
    }

    @Test
    fun delChatFalse() {
        val service = ChatService()
        service.addOutgoingMessage(OutgoingMessage(
                recipientId = 11,
                messageId = 45,
                text = "Привет, потихонечку делаю"
        ))
        val result = service.delChat(98)
        assertFalse(result)
    }

    @Test
    fun delMessageTrue() {
        val service = ChatService()
        service.addOutgoingMessage(OutgoingMessage(
                recipientId = 11,
                messageId = 45,
                text = "Привет, потихонечку делаю"
        ))
        val result = service.delMessage(45)
        assertTrue(result)
    }

    @Test
    fun delMessageFalse() {
        val service = ChatService()
        service.addOutgoingMessage(OutgoingMessage(
                recipientId = 11,
                messageId = 45,
                text = "Привет, потихонечку делаю"
        ))
        val result = service.delMessage(99)
        assertFalse(result)
    }

    @Test
    fun getMessageFromChatTrue() {

        val chatService = ChatService()
        chatService.addIncomingMessage(
                IncomingMessage(
                        author = "Юрий",
                        authorId = 3,
                        messageId = 1,
                        text = "Привет"
                )
        )
        chatService.addOutgoingMessage(
                OutgoingMessage(
                        recipientId = 3,
                        messageId = 2,
                        text = "Здорова Юр, еще РАЗ!"
                )
        )
        chatService.addIncomingMessage(
                IncomingMessage(
                        author = "Анатолий",
                        authorId = 3,
                        messageId = 3,
                        text = "Как дела с ДЗ??"
                )
        )

        val result = chatService.getMessagesFromChat(
                chatId = 3,
                lastMessageId = 1,
                numberOfMessages = 2
        )

        assertTrue(result.size == 2 && result.any { it.readStatus })
    }

    @Test
    fun getMessageFromChatChatIdError() {

        val chatService = ChatService()
        chatService.addIncomingMessage(
                IncomingMessage(
                        author = "Юрий",
                        authorId = 3,
                        messageId = 1,
                        text = "Привет"
                )
        )


        val result = chatService.getMessagesFromChat(
                chatId = 5,
                lastMessageId = 1,
                numberOfMessages = 1
        )

        assertFalse(result.size == 1 && result.any { it.readStatus })
    }

    @Test
    fun getMessageFromChatMessageIdError() {

        val chatService = ChatService()
        chatService.addIncomingMessage(
                IncomingMessage(
                        author = "Юрий",
                        authorId = 3,
                        messageId = 1,
                        text = "Привет"
                )
        )

        val result = chatService.getMessagesFromChat(
                chatId = 3,
                lastMessageId = 55,
                numberOfMessages = 1
        )
        assertFalse(result.size == 1 && result.any { it.readStatus })
    }

    @Test
    fun getMessageFromChatIndexOutOfBoundsExceptionCheck() {

        val chatService = ChatService()
        chatService.addIncomingMessage(
                IncomingMessage(
                        author = "Юрий",
                        authorId = 3,
                        messageId = 1,
                        text = "Привет"
                )
        )

        val result = chatService.getMessagesFromChat(
                chatId = 3,
                lastMessageId = 1,
                numberOfMessages = 5
        )

        assertTrue(result.size == 1 && result.any { it.readStatus })
    }
}