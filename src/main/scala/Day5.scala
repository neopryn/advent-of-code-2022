import cats.syntax.all._

object Day5 {
  def reindexTail[A](state: Map[Int, List[A]], fromIndex: Int, toIndex: Int, amount: Int, flipTail: Boolean) = {
    state ++ Map(
      fromIndex -> state(fromIndex).dropRight(amount),
      toIndex -> (state(toIndex) ::: (
          if (flipTail) {
            state(fromIndex).takeRight(amount).reverse
          } else state(fromIndex).takeRight(amount)
        ))
    )
  }

  def runSim(moves: List[Move], initialState: Map[Int, List[Char]], flip: Boolean) = {
    moves
      .foldLeft(initialState) {
        case (state, move) => reindexTail(state, move.fromIndex, move.toIndex, move.amount, flip)
      }
      .toList.sortBy(_._1).flatMap(_._2.takeRight(1)).mkString
  }

  case class Move(fromIndex: Int, toIndex: Int, amount: Int)

  def solve(inputString: String) = {
    val allLines = inputString.split("\n").toList
    // Always assuming there's a blank line
    val (initialStateWithNums, moveSetWithNL) = allLines.span(_ != "")

    val initialState = initialStateWithNums
      // Remove num row
      .dropRight(1)
      // Turn into char arrays
      .map(_.toCharArray.grouped(4).toList.flatMap(_.unapply(1)).zipWithIndex)
      // Using the magical semigroup "combine", turn into map
      .reverse.foldLeft(Map.empty[Int, List[Char]]) {
        case (state, charList) => charList.foldLeft(state) {
          case (innerState, (char, stackIndex)) =>
            if (!char.isWhitespace) {
              innerState.combine(Map((stackIndex + 1) -> List(char)))
            } else innerState
        }
      }

    val moveRegex = """^move (\d*) from (\d) to (\d)$""".r
    val moves = moveSetWithNL.tail.map {
      case moveRegex(amt, from, to) => Move(from.toInt, to.toInt, amt.toInt)
    }

    val p1 = runSim(moves, initialState, true)
    val p2 = runSim(moves, initialState, false)

    (p1, p2)
  }
}
