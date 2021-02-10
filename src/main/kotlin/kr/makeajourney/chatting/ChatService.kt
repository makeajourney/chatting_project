package kr.makeajourney.chatting

import com.fasterxml.jackson.databind.ObjectMapper
import kr.makeajourney.chatting.dto.ChatRoom
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import java.io.IOException
import java.util.UUID

@Service
class ChatService(
        val objectMapper: ObjectMapper,
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    private val chatRooms: MutableMap<String, ChatRoom> = mutableMapOf()

    fun findAllRoom() = chatRooms.values.toList()

    fun findRoomById(roomId: String) = chatRooms[roomId]

    fun createRoom(name: String) : ChatRoom {
        val randomId = UUID.randomUUID().toString()
        val chatRoom = ChatRoom(randomId, name)
        chatRooms[randomId] = chatRoom

        return chatRoom
    }

    fun <T> sendMessage(session: WebSocketSession, message: T) {
        try {
            session.sendMessage(TextMessage(objectMapper.writeValueAsString(message)))
        } catch (e: IOException) {
            logger.error(e.message, e)
        }
    }
}
