import set.Position
import set.gameSet

fun main() {
    val states = HashMap<Position, State>()
    gameSet(states)

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



