package com.mblinn.mbfpp.oo.tinyweb

import TinyWeb.Filter

class TinyWeb(val controllers: Map[String, Controller], val filters: List[Filter]) {

  lazy val composedFilter = filters reduceLeft {
    (composed, next) => composed andThen next
  }

  def handleRequest(request: HttpRequest): Option[HttpResponse] = {
    val filteredRequest = composedFilter(request)
    controllers get (filteredRequest.path) map {
      controller => controller handleRequest filteredRequest
    }
  }

}

object TinyWeb {
  type Filter = (HttpRequest) => HttpRequest
}

