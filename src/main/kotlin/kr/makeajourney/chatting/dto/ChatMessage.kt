package kr.makeajourney.chatting.dto

data class ChatMessage(
        val roomId: String,
        val sender: String,
        var message: String,
        val type: MessageType,
)
