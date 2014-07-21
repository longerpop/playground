package com.mblinn.mbfpp.oo.tinyweb

final case class HttpRequest(headers: Map[String, String], body: String, path: String)
final case class HttpResponse(body: String, responseCode: Int)

