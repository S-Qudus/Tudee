
data class MessageState(
    val text: String,
    val type: MessageType = MessageType.INFO,
    val isVisible: Boolean = true
)

enum class MessageType {
    SUCCESS, ERROR, INFO
}

fun MessageState.hide(): MessageState {
    return this.copy(isVisible = false)
}