package com.ebarrientos

trait Painter {
  def paint(puntos: Seq[(Double, Double)]): Unit
}

object DoodlePainter extends Painter {
  import doodle.core._
  import doodle.core.Image._
  import doodle.syntax._
  import doodle.jvm.Java2DCanvas._
  import doodle.backend.StandardInterpreter._

  val circleRadius = 1.0
  val circleColor = Color.blue

  def toImage(p: (Double, Double)): Image =
    (Circle(circleRadius) fillColor circleColor) at (p._1, p._2)

  def paint(puntos: Seq[(Double, Double)]): Unit = {
    allOn(puntos.map(toImage)).draw
  }
}

// Painter that uses straight swing
class GraphicsPainter(width: Int, height: Int) extends Painter {
  import javax.swing.JFrame
  import javax.swing.JPanel

  private[this] val radius = 2

  val (frame, graphics) = {
    val f = new JFrame("Chaos Game")
    f.setSize(width, height)
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

    val panel = new JPanel()
    f.getContentPane().add(panel)

    f.setVisible(true)
    f.setLocationRelativeTo(null)

    (f, panel.getGraphics())
  }

  // Convert dd coordinate to ii coordinate
  private[this] def pd2pi(p: (Double, Double)): (Int, Int) =
    (p._1.toInt, p._2.toInt)

  // Draw ONE point
  private[this] def drawPoint(p: (Int, Int)) = {
    val (x, y) = p
    // graphics.drawLine(x, y, x, y)
    graphics.fillOval(x, y, radius, radius)
  }

  def paint(puntos: Seq[(Double, Double)]): Unit = {
    for (p <- puntos) drawPoint(pd2pi(p))
  }
}
