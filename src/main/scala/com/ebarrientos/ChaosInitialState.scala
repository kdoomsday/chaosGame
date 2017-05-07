package com.ebarrientos

import com.ebarrientos.ChaosGame.{ Punto, randomPoint }

/** Initial game state */
final case class InitialState(
  width: Int,
  height: Int,
  points: IndexedSeq[Punto],
  start: Punto,
  iterations: Int,
  distanceFactor: Double
)

trait InitialStateLoader {
  def load(): InitialState
}

/** Creates a fully random initial state */
object RandomInitialState extends InitialStateLoader {
  def load() = {
    val w = 1024
    val h = 768
    val nPoints = 3
    val iterations = 10000

    InitialState(
      width = w,
      height = h,
      points = Array.fill(nPoints)(randomPoint(w, h)),
      start = randomPoint(w, h),
      iterations = iterations,
      distanceFactor = 0.5
    )
  }
}

object CompiledInitialState extends InitialStateLoader {
  val w = 1024
  val h = 768
  val start = (300.0, 300.0)
  val positions = IndexedSeq(
    // (50.0, 150.0),
    // (512.0, 20.0),
    // (974.0, 150.0),
    // (904.0, 658.0),
    // (120.0, 658.0)
    (50.0, 30.0),
    (950.0, 30.0),
    (50.0, 630.0),
    (950.0, 630.0)
  )
  val iterations = 200000
  val factor = 1 / 3.0

  def load() =
    InitialState(w, h, positions, start, iterations, factor)
}
