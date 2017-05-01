package com.ebarrientos

import scala.util.Random

/** Does calculations for chaos game */
class ChaosCalc(
    width: Int,
    height: Int,
    nPoints: Int = 3,
    iterations: Int = 100
) {
  require(width > 0)
  require(height > 0)

  type Punto = (Double, Double)

  private[this] val positions = Array.fill(nPoints)(randomPoint())
  private[this] def startPos = randomPoint()

  def randomPoint(): Punto = {
    val x = scala.util.Random.nextDouble() * width
    val y = scala.util.Random.nextDouble() * height
    (x, y)
  }

  // Medio camino entre un punto y el otro
  def halfPoint(p1: Punto, p2: Punto): Punto = {
    val (x1, y1) = p1
    val (x2, y2) = p2
    ((x1 + x2) / 2.0, (y1 + y2) / 2.0)
  }

  /** Randomly select one of the positions */
  @inline
  def nextPos(): Punto = {
    positions(Random.nextInt(nPoints))
  }

  /** Get all points after playing all iterations */
  private[this] def findPoints(iterations: Int, prev: List[Punto]): List[Punto] =
    if (iterations <= 0) prev
    else prev match {
      case p :: ps ⇒ findPoints(iterations - 1, halfPoint(p, nextPos()) :: prev)
      case _ ⇒ throw new Exception("WTF")
    }

  /** Play the game and return the result */
  def play(): Seq[Punto] = findPoints(iterations, startPos :: positions.toList)
}
