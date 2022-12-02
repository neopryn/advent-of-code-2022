import fs from 'fs'

enum Move {
  Rock = 1,
  Paper = 2,
  Scissors = 3
}

enum Outcome {
  Draw = 0,
  Win = 1,
  Lose = 2
}


const scoreMoveMove = (moves: [Move, Move]) => {
  if (moves[0] === moves[1]) {
    return 3 + moves[0]
  } else {

    switch (moves[0].toString() + moves[1].toString()) {
      case '13':
      case '21':
      case '32':
        return 6 + moves[0]
      default:
        return 0 + moves[0]
    }
  }
}

const scoreMoveOutcome = (pair: [Move, Outcome]) => {
  switch (pair[1]) {
    case Outcome.Draw:
      console.log(pair)
      return 3 + pair[0]
    default:
      switch (pair[0]) {
        case Move.Rock:
          return pair[1] === Outcome.Win ? 8 : 3
        case Move.Paper:
          return pair[1] === Outcome.Win ? 9 : 1
        case Move.Scissors:
          return pair[1] === Outcome.Win ? 7 : 2
      }
  }
}

const decodeMove = (val: string) => {
  switch(val) {
    case 'A':
    case 'X':
      return Move.Rock

    case 'B':
    case 'Y':
      return Move.Paper

    case 'C':
    case 'Z':
      return Move.Scissors

    default:
      throw new Error('Unable to decode move: ' + val)
  }
}

const decodeOutcome = (val: string) => {
  switch (val) {
    case 'X':
      return Outcome.Lose
    case 'Y':
      return Outcome.Draw
    case 'Z':
      return Outcome.Win
  }
}

const stringContents = fs.readFileSync(__dirname + '/input', 'utf-8')
const splitIntPairs = stringContents.split('\n').map(pair => pair.split(' '))


const p1 = splitIntPairs.map(pair => pair.map(decodeMove) as [Move, Move])
  .map(movePair => scoreMoveMove([movePair[1], movePair[0]]))
  .reduce((cur, next) => next + cur, 0 as number)

console.log(p1)

const p2 = splitIntPairs.map(pair => [decodeMove(pair[0]), decodeOutcome(pair[1])] as [Move, Outcome])
  .map(pair => scoreMoveOutcome(pair))
  .reduce((cur, next) => next + cur, 0 as number)

console.log(p2)