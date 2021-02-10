package kr.makeajourney.chatting

import kr.makeajourney.chatting.dto.ChatRoom
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ChatController(
        val chatService: ChatService
) {

    @PostMapping("/chat")
    fun createRoom(@RequestParam name: String) : ChatRoom {
        return chatService.createRoom(name)
    }

    @GetMapping("/chat")
    fun findAllRoom() : List<ChatRoom> {
        return chatService.findAllRoom()
    }
}