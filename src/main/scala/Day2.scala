import cats.syntax.all._

object Day2 {

  enum Move(val score: Int) {
    case Rock extends Move(1)
    case Paper extends Move(2)
    case Scissors extends Move(3)
  }


  enum Outcome(val score: Int) {
    case Draw extends Outcome(3)
    case Win extends Outcome(6)
    case Lose extends Outcome(0)
  }

  def decodeMove(value: String): Move = value match {
    case "A" | "X" => Move.Rock
    case "B" | "Y" => Move.Paper
    case "C" | "Z" => Move.Scissors
    case _ => throw new Exception("Unknown move")
  }

  def decodeOutcome(value: String): Outcome = value match {
    case "X" => Outcome.Lose
    case "Y" => Outcome.Draw
    case "Z" => Outcome.Win
    case _ => throw new Exception("Unknown outcome")
  }

  def deriveOutcome(other: Move, you: Move): Outcome = (other, you) match {
    case (a, b) if a == b => Outcome.Draw
    case (Move.Scissors, Move.Rock) | (Move.Paper, Move.Scissors) | (Move.Rock, Move.Paper) => Outcome.Win
    case _ => Outcome.Lose
  }

  def deriveMove(other: Move, outcome: Outcome): Move = outcome match {
    case Outcome.Draw => other
    case Outcome.Win => other match {
      case Move.Rock => Move.Paper
      case Move.Paper => Move.Scissors
      case Move.Scissors => Move.Rock
    }
    case Outcome.Lose => other match {
      case Move.Rock => Move.Scissors
      case Move.Paper => Move.Rock
      case Move.Scissors => Move.Paper
    }
  }

  def solve(inputString: String) = {
    val pairs = inputString.split('\n').map(pair => pair.split(' '))

    val p1 = pairs.map(_.map(decodeMove)).map {
      case Array(other, you) => deriveOutcome(other, you).score + you.score
    }.sum

    val p2 = pairs.map {
      case Array(other, outcome) => (decodeMove(other), decodeOutcome(outcome))
    }.map(x => deriveMove(x._1, x._2).score + x._2.score).sum

    (p1, p2)
  }
}
