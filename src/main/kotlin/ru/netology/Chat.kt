package ru.netology

data class Chat(
    val id: Int = -1,
    var incomingMessage: List<IncomingMessage> = emptyList(),
    var outgoingMessage: List<OutgoingMessage> = emptyList(),
) {
    override fun toString(): String {
        return "ChatId= $id, inMess= $incomingMessage, outMess= $outgoingMessage\n"
    }
}