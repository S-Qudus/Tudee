data class MessageState(
    val text: String,
    val type: MessageType = MessageType.SUCCESS
) {
    companion object {
        fun success(message: String): MessageState {
            return MessageState(
                text = message,
                type = MessageType.SUCCESS
            )
        }
        
        fun error(message: String): MessageState {
            return MessageState(
                text = message,
                type = MessageType.ERROR
            )
        }
    }
}

enum class MessageType {
    SUCCESS, ERROR
}