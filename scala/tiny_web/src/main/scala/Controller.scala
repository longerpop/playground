package com.mblinn.mbfpp.oo.tinyweb

trait Controller {
  def handleRequest(httpRequest: HttpRequest): HttpResponse
}

object Controller {
  final class ControllerException(val statusCode: Int) extends Exception
}


class FunctionController(view: View, doRequest: (HttpRequest) => Map[String, List[String]]) extends Controller {

  import Controller.ControllerException
  import View.RenderingException

  def handleRequest(httpRequest: HttpRequest) =
    try HttpResponse(view render doRequest(httpRequest), 200) catch {
      case ce : ControllerException => HttpResponse("", ce.statusCode)
      case re : RenderingException  => HttpResponse("Exception while rendering.", 500)
      case _  : Throwable           => HttpResponse("", 500)
    }

}

