object Day4 {
  def solve(inputString: String) = {
    val inputRegex = """^(\d*)-(\d*),(\d*)-(\d*)$""".r

    val jobPairs = inputString.split("\n").toList
      .map {
        case inputRegex(s1, e1, s2, e2) => ((s1.toInt to e1.toInt).toList, (s2.toInt to e2.toInt).toList)
      }

    val p1 = jobPairs.count {
      // Distinct list size of biggest range shouldn't change
      case (r1, r2) => (r1 ::: r2).distinct.length == (r1.length max r2.length)
    }

    val p2 = jobPairs.count {
      // List size after distinct should match length
      case (r1, r2) => (r1 ::: r2).distinct.length != (r1.length + r2.length)
    }

    (p1, p2)
  }
}
