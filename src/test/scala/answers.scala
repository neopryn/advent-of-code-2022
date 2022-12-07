import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source

class answers extends AnyFunSuite {
  test("1") {
    val input = Source.fromResource("1").mkString

    println(Day1.solve(input))
  }

  test("2") {
    val input = Source.fromResource("2").mkString

    println(Day2.solve(input))
  }

  test("3") {
    val input = Source.fromResource("3").mkString

    println(Day3.solve(input))
  }

  test("4") {
    val input = Source.fromResource("4").mkString

    println(Day4.solve(input))
  }

  test("5") {
    val input = Source.fromResource("5").mkString

    println(Day5.solve(input))
  }

  test("6") {
    val input = Source.fromResource("6").mkString

    println(Day6.solve(input))
  }
}
