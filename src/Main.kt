fun main() {
    val states = HashMap<Article, State>()

    states[Article.Start] = State(
        message = "Начнём экзамен.",
        nextArticle = Article.Q1,
    )

    states[Article.Q1] = State(
        message = "Первый вопрос",
        question = "Какой предмет сдаёте?",
//        options = "Программирование, Не знаю",
        options = "1. Программирование, 2.Не знаю",
//        trueAnswer = "Программирование",
        trueAnswer = "1",
        nextArticle = Article.Q2,
        falseJmp = Article.BadFinish
    )

    states[Article.Q2] = State(
        message = "Второй вопрос",
        question = "Как меня зовут?",
        options = "1. Иван Иваныч, 2. Не знаю",
//        options = "Иван Иваныч, Не знаю",
//        trueAnswer = "Иван Иваныч",
        trueAnswer = "1",
        nextArticle = Article.Q3,
        falseJmp = Article.BadFinish
    )


    states[Article.Q3] = State(
        message = "Третий вопрос",
        question = "Какой язык изучали?",
//        options = "Java, Не знаю",
        options = "1. Java, 2. Не знаю",
//        trueAnswer = "Java",
        trueAnswer = "1",
        nextArticle = Article.GoodFinish,
        falseJmp = Article.BadFinish
    )

    states[Article.GoodFinish] = State(
        message = "Ставлю отлично",
        nextArticle = Article.Finish
    )

    states[Article.BadFinish] = State(
        message = "Переэкзаменовка!",
        nextArticle = Article.Finish
    )


    states[Article.Finish] = State(
        message = "The END",
    )

    var state = Article.Start

    do {
        state =  states[state]!!.answer()
    } while (state != Article.Finish)
}

enum class Article {
    Start,
    Q1, Q2, Q3, GoodFinish,
    BadFinish,
    Finish
}

class State(
    val message: String,
    val question: String = "",
    val options: String = "",
    val trueAnswer: String = "",
    val nextArticle: Article = Article.Finish,
    val falseJmp: Article = nextArticle,
) {
    fun answer(): Article {
        println("$message")
        println(question)
        println(options)

        if (question.isNotBlank()) {
            val answer = readLine()?.lowercase()?.trim()
            if (answer.contentEquals(trueAnswer)) return nextArticle
            else return falseJmp
        } else return nextArticle
    }

}