package kr.makeajourney.chatting

import com.fasterxml.jackson.databind.ObjectMapper
import kr.makeajourney.chatting.dto.ChatMessage
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class WebSocketHandler(
        val objectMapper: ObjectMapper,
        val chatService: ChatService,
) : TextWebSocketHandler() {
    val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val payload = message.payload
        logger.info("payload {}", payload)
        val chatMessage = objectMapper.readValue(payload, ChatMessage::class.java)
        val room = chatService.findRoomById(chatMessage.roomId)
        room?.handleActions(session, chatMessage, chatService)
    }
}
