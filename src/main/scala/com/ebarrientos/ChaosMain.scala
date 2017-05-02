package com.ebarrientos

object ChaosMain extends App {
  val (width, height) = (1024, 768)

  val nPoints = 5
  val iterations = 100000

  val painter = new GraphicsPainter(width, height)
  val state = CompiledInitialState.load()

  painter paint ChaosCalc.play(state)
}
