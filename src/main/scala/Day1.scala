object Day1 {
  def solve(inputString: String) = {

    val elves = inputString.split("\n\n")
      .map(_.split("\n").map(_.toInt).sum)

    (
      elves.max,
      elves.sorted.reverse.take(3).sum
    )
  }
}
