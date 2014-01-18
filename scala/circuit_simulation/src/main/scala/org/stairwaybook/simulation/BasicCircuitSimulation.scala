package org.stairwaybook.simulation

abstract class BasicCircuitSimulation extends Simulation {

  def InverterDelay : Int
  def AndGateDelay  : Int
  def OrGateDelay   : Int


  class Wire {

    private var sigVal  : Boolean      = false
    private var actions : List[Action] = List()

    def signal : Boolean = sigVal

    def signal_=(s : Boolean) : Unit =
      if (s != sigVal) {
        sigVal = s
        actions foreach (_ ())
      }

    def addAction(a : Action) : Unit = {
      actions = a :: actions
      a()
    }

  }


  def inverter(input : Wire, output : Wire) = {
    def inverterAction() =
      afterDelay(InverterDelay) {
        output.signal = !input.signal
      }

    input addAction inverterAction
  }

  def andGate(a1 : Wire, a2 : Wire, output : Wire) = {
    def andAction() =
      afterDelay(AndGateDelay) {
        output.signal = a1.signal & a2.signal
      }

    a1 addAction andAction
    a2 addAction andAction
  }

  def orGate(o1 : Wire, o2 : Wire, output : Wire) = {
    def orAction() =
      afterDelay(OrGateDelay) {
        output.signal = o1.signal | o2.signal
      }

    o1 addAction orAction
    o2 addAction orAction
  }

  def probe(name : String, wire : Wire) = {
    def probeAction() =
      println(s"$name $currentTime new-value = ${wire.signal}")

    wire addAction probeAction
  }

}
