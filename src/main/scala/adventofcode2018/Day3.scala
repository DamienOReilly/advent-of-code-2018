package adventofcode2018

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object Day3 extends App {

  val claims = Source.fromResource("day3.txt")
    .mkString
    .split("\n")

  // #3 @ 378,335: 22x18
  val pattern = "#([0-9]+) @ ([0-9]+),([0-9]+): ([0-9]+)x([0-9]+)".r

  var matrix = ArrayBuffer.fill(1200, 1200)(0)

  val parsedClaims = claims.map(claim => {
    val pattern(a, b, c, d, e) = claim
    (a.toInt, b.toInt, c.toInt, d.toInt, e.toInt)
  })

  parsedClaims.foreach(cords =>
    for {
      x <- 0 until cords._4
      y <- 0 until cords._5
    } yield matrix(x + cords._2)(y + cords._3) += 1
  )

  val part1 = (for {
    i <- 0 until 1200
    j <- 0 until 1200
    if matrix(i)(j) > 1
  } yield 1).sum

  println(s"Part 1: $part1")

  parsedClaims.foreach(claim => {
    val result = for {
      i <- claim._2 until claim._2 + claim._4
      j <- claim._3 until claim._3 + claim._5
      if matrix(i)(j) == 1
    } yield 1

    if (result.size == claim._4 * claim._5)
      println(s"Part 2: # ${claim._1}")
  })

}
