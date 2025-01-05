import set.Position

class State(
    val message: String,
    val question: String = "",
    val options: String = "",
    val trueAnswer: String = "",
    val nextPosition: Position? = null,
    val falseJmp: Position? = Position.BadFinish,
) {
    fun invoke(): Position? {
        println(message)

        if (question.isNotBlank()) println(question)

        if (options.isNotBlank()) println(options)

        if (question.isNotBlank()) {
            val answer = readLine()?.lowercase()?.trim()
            if (answer.contentEquals(trueAnswer)) return nextPosition
            else return falseJmp
        } else return nextPosition
    }

}