package com.ebarrientos

import com.ebarrientos.ChaosGame.Punto
import scala.annotation.tailrec
import scala.util.Random

/** Does calculations for chaos game */
object ChaosCalc {

  // Medio camino entre un punto y el otro
  private[this] def halfPoint(p1: Punto, p2: Punto): Punto = {
    val (x1, y1) = p1
    val (x2, y2) = p2
    ((x1 + x2) / 2.0, (y1 + y2) / 2.0)
  }

  /** Randomly select one of the positions */
  @inline
  private[this] def nextPos(positions: IndexedSeq[Punto]): Punto = {
    positions(Random.nextInt(positions.size))
  }

  /** Get all points after playing all iterations */
  @tailrec
  private[this] def findPoints(
    iterations: Int,
    lastPoint: Punto,
    prev: List[Punto],
    positions: IndexedSeq[Punto]
  ): List[Punto] = {
    if (iterations <= 0) prev
    else {
      val point = halfPoint(lastPoint, nextPos(positions))
      findPoints(iterations - 1, point, point :: prev, positions)
    }
  }

  /** Play the game and return the result */
  def play(state: InitialState): Seq[Punto] =
    findPoints(state.iterations, state.start, state.start :: state.points.toList, state.points)
}
