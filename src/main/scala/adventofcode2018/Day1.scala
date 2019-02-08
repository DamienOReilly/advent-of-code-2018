package adventofcode2018

import scala.annotation.tailrec
import scala.io.Source

object Day1 extends App {

  // part 1
  val freqz = Source.fromResource("day1.txt")
    .mkString
    .split("\n").map(_.toInt)

  println(s"Part 1: ${freqz.sum}")

  val streamCulumatively = Stream.continually(freqz)
    .flatten
    .scan(0)(_ + _)

  // part 2
  def findDup(stream: Stream[Int]): Int = {

    @tailrec
    def loop(remaining: Stream[Int], frequencies: Set[Int]): Int = {
      val current = remaining.head
      if (frequencies.contains(current))
        current
      else {
        loop(remaining.tail, frequencies + current)
      }
    }

    loop(stream, Set.empty)
  }
  println(s"Part 2: ${findDup(streamCulumatively)}")

}
