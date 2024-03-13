package me.haile.nationalparks.data.openai

data class ChatRequest(
    val model: String,
    val messages: List<ChatMessage>
)
data class ChatMessage(
    val role: String,
    val content: String
)
