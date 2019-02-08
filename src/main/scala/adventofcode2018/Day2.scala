package adventofcode2018

import scala.io.Source

object Day2 extends App {

  val ids = Source.fromResource("day2.txt")
    .mkString
    .split("\n")

  //part 1
  val step1 = ids.map { id =>
    id.groupBy(_.toChar).map(c => (c._1, c._2.length))
  }

  val step2 = step1.map {
    _.filter(c => c._2 > 1 && c._2 < 4)
      .values
      .toSet
  }

  val twos = step2.foldLeft(0) { (x, y) => if (y(2)) x + 1 else x }
  val threes = step2.foldLeft(0) { (x, y) => if (y(3)) x + 1 else x }

  println(s"Part 1: ${twos * threes}")

  //part 2
  val wow = ids
    .toList
    .combinations(2)
    .foreach { x =>
      // only choose those where the difference is 1 char
      if (x(0).diff(x(1)).length == 1) {
        // find offset if different char
        val offset = (x(0) zip x(1)).indexWhere {
          case (a,b) => a != b
        }
        println(s"${x(0)} - ${x(1)} -> ${x(0).diff(x(1))}")
      }
    }



}
