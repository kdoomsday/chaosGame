package com.ebarrientos

object ChaosGame {
  type Punto = (Double, Double)

  /**
   * Produce a random point inside a square described by width and height
   * The x coordinate is guaranteed to be inside [0, width]
   * The y coordinate is guaranteed to be inside [0, height]
   */
  def randomPoint(width: Int, height: Int): Punto = {
    val x = scala.util.Random.nextDouble() * width
    val y = scala.util.Random.nextDouble() * height
    (x, y)
  }

}
