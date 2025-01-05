fun main() {
    val states = HashMap<Position, State>()

    states[Position.Start] = State(
        message = "Начнём экзамен.",
        nextPosition = Position.Q1,
    )

    states[Position.Q1] = State(
        message = "Первый вопрос",
        question = "Какой предмет сдаёте?",
        options = "1. Программирование, 2.Не знаю",
        trueAnswer = "1",
        nextPosition = Position.Q2,
        falseJmp = Position.BadFinish
    )

    states[Position.Q2] = State(
        message = "Второй вопрос",
        question = "Как меня зовут?",
        options = "1. Иван Иваныч, 2. Не знаю",
        trueAnswer = "1",
        nextPosition = Position.Q3,
        falseJmp = Position.BadFinish
    )


    states[Position.Q3] = State(
        message = "Третий вопрос",
        question = "Какой язык изучали?",
        options = "1. Java, 2. Не знаю",
//        options = "Java, Не знаю",
//        trueAnswer = "Java",
        trueAnswer = "1",
        nextPosition = Position.GoodFinish,
        falseJmp = Position.BadFinish
    )

    states[Position.GoodFinish] = State(
        message = "Ставлю отлично",
        nextPosition = Position.Finish
    )

    states[Position.BadFinish] = State(
        message = "Переэкзаменовка!",
        nextPosition = Position.Finish
    )

    states[Position.Finish] = State(
        message = "The END",
    )

    var position = Position.Start

    do {
        val state = states[position]!!
        val nextState = state.invoke()

        position =
            if (nextState != null) nextState
            else Position.entries[position.ordinal + 1]

    } while (position != Position.Finish)

    states[Position.Finish]!!.invoke()
}

enum class Position {
    Start,
    Q1, Q2, Q3,
    GoodFinish,
    BadFinish,
    Finish
}

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