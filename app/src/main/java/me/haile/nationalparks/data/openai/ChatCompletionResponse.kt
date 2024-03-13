package me.haile.nationalparks.data.openai

data class ChatCompletionResponse(
    val id: String,
    val created: Long,
    val model: String,
    val choices: List<Choice>,
    val usage: Usage,
    val systemFingerprint: String
)

data class Choice(
    val index: Int,
    val message: Message,
    val logprobs: Any?, // Can be replaced with a more specific type if needed.
    val finishReason: String
)

data class Message(
    val role: String,
    val content: String
)

data class Usage(
    val promptTokens: Int,
    val completionTokens: Int,
    val totalTokens: Int
)