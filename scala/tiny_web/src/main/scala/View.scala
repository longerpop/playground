package com.mblinn.mbfpp.oo.tinyweb

trait View {
  def render(model: Map[String, List[String]]): String
}

object View {
  final class RenderingException extends Exception
}


class FunctionView(viewRenderer: (Map[String, List[String]]) => String) extends View {

  import View.RenderingException

  def render(model: Map[String, List[String]]) =
    try viewRenderer(model) catch {
      case _ : RenderingException => ""
    }

}



