object Day3 {
  def solve(inputString: String) = {
    val charValues = ('a' to 'z').appendedAll('A' to 'Z')
      .zipWithIndex.map(zi => (zi._1, zi._2 + 1)).toMap

    val p1 = inputString.split("\n")
      .flatMap(rs => {
        // Bold assumption that RS is in fact even
        val (c1, c2) = rs.toCharArray.toList.splitAt(rs.length / 2)

        // Assuming only a single Char will intersect and ignore doubles
        c1.intersect(c2).take(1)
      })
      .map(charValues(_))
      .sum

    val p2 = inputString.split("\n").map(_.toCharArray.toList).grouped(3).flatMap {
      case Array(rs1, rs2, rs3) => rs1.intersect(rs2).intersect(rs3).take(1)
    }.map(charValues(_)).sum

    (p1, p2)
  }
}
