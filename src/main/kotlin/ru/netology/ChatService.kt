package ru.netology

class ChatService {
    private var chats = mutableListOf<Chat>()

    fun addOutgoingMessage(message: OutgoingMessage): Int {


        chats.forEach {
            if (it.id == message.authorId) {
                it.outgoingMessage += message
                return it.outgoingMessage.size
            }
        }
        chats.add(Chat(id = message.recipientId, outgoingMessage = listOf(message)))
        return chats.size
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

    fun getMessagesFromChat(chatId: Int, lastMessageId: Int, numberOfMessages: Int): List<IncomingMessage> {
        chats.forEach { chat ->
            if (chat.id == chatId) {
                if (!chat.incomingMessage.any { it.messageId == lastMessageId }) {
                    println("ID последнего сообщения не существует")
                    return emptyList()
                }
                chat.incomingMessage.forEachIndexed { index, incomingMessage ->
                    if (incomingMessage.messageId == lastMessageId) {
                        var messages = chat.incomingMessage.subList(
                                fromIndex = index,
                                toIndex = chat.incomingMessage.size
                        )
                        val indexOutOfBoundsExceptionCheck = if (numberOfMessages <= messages.size) {
                            numberOfMessages
                        } else {
                            messages.size
                        }
                        messages.subList(fromIndex = index, toIndex = indexOutOfBoundsExceptionCheck)
                        messages.forEach { messages: IncomingMessage -> messages.readStatus = true }
                        return messages
                    }
                }
            }
        }
        println("Чат ID не найден")
        return emptyList()
    }

    fun getUnreadChatsCount(): Int {
        return chats.filter { chat -> chat.incomingMessage.any { !it.readStatus } && chat.incomingMessage.isNotEmpty() }
                .count()
    }

    fun getChatList(): List<Chat> {
        return chats.toList()
    }
}