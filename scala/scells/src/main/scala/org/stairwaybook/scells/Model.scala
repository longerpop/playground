package org.stairwaybook.scells

import swing._

class Model(val height : Int, val width : Int) extends Evaluator with Arithmetic {

  case class ValueChanged(cell : Cell) extends event.Event

  case class Cell(row : Int, column : Int) extends Publisher {

    private var v : Double  = 0
    private var f : Formula = Empty

    def value   : Double  = v
    def formula : Formula = f

    def value_=(w : Double) : Unit =
      if (v != w && !(v.isNaN && w.isNaN)) {
        v = w
        publish(ValueChanged(this))
      }

    def formula_=(g : Formula) : Unit = {
      for (c <- references(formula)) deafTo(c)
      f = g
      for (c <- references(formula)) listenTo(c)
      value = evaluate(f)
    }

    override def toString =
      formula match {
        case Textual(s) => s
        case _          => value.toString
      }

    reactions += {
      case ValueChanged(_) => value = evaluate(formula)
    }

  }

  val cells : Array[Array[Cell]] =
    Array.tabulate(height, width) {
      (i, j) => new Cell(i, j)
    }

}
