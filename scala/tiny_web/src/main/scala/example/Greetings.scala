package com.mblinn.mbfpp.oo.tinyweb.example

import scala.util.Random
import com.mblinn.mbfpp.oo.tinyweb._

object Greetings {

  lazy val greetings    = Vector("Hello", "Greetings", "Salutations", "Hola")
  lazy val greetingView = new FunctionView(greetingViewRenderer)
  lazy val greetingCtrl = new FunctionController(greetingView, handleGreetingRequest)
  lazy val greetingWeb  = new TinyWeb(Map("/greeting" -> greetingCtrl), List(loggingFilter))

  private def greetingViewRenderer(model: Map[String, List[String]]) = {
    def renderGreeting(greeting: String) = s"<h2>$greeting</h2>"

    s"<h1>Friendly Greetings:</h1> ${
      model.getOrElse("greetings", List.empty) map renderGreeting mkString ", "
    }"
  }

  private def handleGreetingRequest(request: HttpRequest) = {
    def makeGreeting(name: String) = s"${ greetings(Random nextInt greetings.size) }, $name"

    Map("greetings" -> (request.body split "," map makeGreeting).toList)
  }

  private def loggingFilter(request: HttpRequest) = {
    println(s"In Logging Filter - request for path: ${request.path}")
    request
  }

  def main(args: Array[String]): Unit =
    greetingWeb handleRequest HttpRequest(Map.empty, "Mike,Joe,John,Steve", "/greeting") foreach {
      case HttpResponse(body, code) =>
        println(s"responseCode: $code")
        println(s"responseBody: $body")
    }

}

