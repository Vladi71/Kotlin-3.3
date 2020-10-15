package ru.netology

class ChatService {
    private var chats = mutableListOf<Chat>()

    fun addOutgoingMessage(message: OutgoingMessage): Boolean {
        chats
                .filter { it.id == message.authorId }
                .map {it.outgoingMessage += message  }
                return true


        chats.add(Chat(id = message.recipientId, outgoingMessage = listOf(message)))
        return true
    }

    fun addIncomingMessage(message: IncomingMessage): Int {
        chats.forEach {
            if (it.id == message.authorId) {
                it.incomingMessage += message
                return it.incomingMessage.size
            }

        }
        chats.add(Chat(id = message.authorId, incomingMessage = listOf(message)))
        return chats.size
    }

    fun editMessage(messageId: Int, editedText: String): Boolean {
        chats.forEach { value ->
            value.outgoingMessage.forEach {
                if (it.messageId == messageId) {
                    it.text = editedText
                    return true
                }
            }
        }
        println("ID не существует")
        return false
    }

    fun delChat(chatId: Int): Boolean {
        val iterator = chats.iterator()
        iterator.forEach {
            if (it.id == chatId) {
                iterator.remove()
                println("Чат удален")
                return true
            }
        }
        println("ID не существует")
        return false
    }

    fun delMessage(messageId: Int): Boolean {
        val iterator = chats.iterator()
        iterator.forEach { chat: Chat ->
            if (chat.outgoingMessage.any { it.messageId == messageId }) {
                val newChat = chat.outgoingMessage.filter { it.messageId != messageId }
                chat.outgoingMessage = newChat
                if (chat.outgoingMessage.isEmpty() && chat.incomingMessage.isEmpty()) {
                    iterator.remove()
                }
                return true

            }
        }
        println("ID сообщения не существует")
        return false

    }

    fun getMessagesFromChat(
            chatId: Int,
            lastMessageId: Int,
            numberOfMessages: Int
    ): List<IncomingMessage> {
        val chat = chats.find {
            it.id == chatId
        } ?: return run {
            println("Чат ID не найден")
            emptyList()
        }

        if (!chat.incomingMessage.any { it.messageId == lastMessageId }) {
            println("ID последнего сообщения не существует")
            return emptyList()
        }
        return chat.incomingMessage.asSequence().filter {
            it.messageId >= lastMessageId
        }.take(numberOfMessages).apply {
            forEach { it.readStatus = true }
        }.toList()
    }

    fun getUnreadChatsCount(): Int {
        return chats.filter { chat -> chat.incomingMessage.any { !it.readStatus } && chat.incomingMessage.isNotEmpty() }
                .count()
    }

    fun getChatList(): List<Chat> {
        return chats.toList()
    }
}