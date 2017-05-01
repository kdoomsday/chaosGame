package com.ebarrientos

object ChaosMain extends App {
  val (width, height) = (1024, 768)

  val nPoints = 5
  val iterations = 100000

  val calc = new ChaosCalc(width, height, nPoints, iterations)
  val painter = new GraphicsPainter(width, height)

  painter paint calc.play()
}
