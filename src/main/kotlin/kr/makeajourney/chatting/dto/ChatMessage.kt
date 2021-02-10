package kr.makeajourney.chatting.dto

data class ChatMessage(
        val id: String,
        val chatRoomId: String,
        val sender: String,
        var message: String,
        val messageType: MessageType,
) {
}