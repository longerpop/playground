package org.stairwaybook.simulation

abstract class Simulation {

  type Action = ( ) => Unit

  case class WorkItem(time : Int, action : Action)

  private var curtime : Int = 0
  private var agenda  : List[WorkItem] = List()

  def currentTime : Int = curtime

  def afterDelay(delay : Int)(block : => Unit) : Unit =
    agenda = insert(agenda, WorkItem(currentTime + delay, ( ) => block))

  def run() : Unit = {
    afterDelay(1) {
      println(s"*** simulation started, time = $currentTime ***")
    }
    while (!agenda.isEmpty) next()
  }

  private def insert(ag : List[WorkItem], item : WorkItem) : List[WorkItem] =
    if (ag.isEmpty || item.time < ag.head.time)
      item :: ag
    else
      ag.head :: insert(ag.tail, item)

  private def next( ) : Unit =
    (agenda : @unchecked) match {
      case item :: rest =>
        agenda  = rest
        curtime = item.time
        item.action()
    }

}

