import cats.syntax.all.*

object Day6 {
  def checkDistinct(inputString: String, range: Int) = {
    inputString.toCharArray.toList.sliding(range).zipWithIndex
      .find(c => c._1.distinct.length == range).map(_._2).getOrElse(0) + range
  }

  def solve(inputString: String) = {
    val p1 = checkDistinct(inputString, 4)
    val p2 = checkDistinct(inputString, 14)

    (p1, p2)
  }
}
