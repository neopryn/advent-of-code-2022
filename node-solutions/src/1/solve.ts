import fs from 'fs'

const stringContents = fs.readFileSync(__dirname + '/input', 'utf-8')
const splitIntoElves = stringContents.split('\n\n')
  .map(elf => elf.split('\n').reduce((cur, next) => parseInt(next) + cur, 0))

const sorted = splitIntoElves.sort((a, b) => b - a)

// P1
console.log(sorted[0])

// P2 im lazy no defined checks
const[one, two, three, ..._] = sorted
console.log(one + two + three)