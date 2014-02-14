import scala.util.parsing.combinator._

class JSON extends JavaTokenParsers {

  def obj : Parser[Map[String, Any]] =
    "{" ~> repsep(member, ",") <~ "}" ^^ (Map() ++ _)

  def arr : Parser[List[Any]] =
    "[" ~> repsep(value, ",") <~  "]"

  def member : Parser[(String, Any)] =
    stringLiteral ~ ":" ~ value ^^ {
      case name ~ ":" ~ value => (name, value)
    }

  def value : Parser[Any] = (
      obj
    | arr
    | stringLiteral
    | floatingPointNumber ^^ (_.toDouble)
    | "null" ^^ (_ => null)
    | "true" ^^ (_ => true)
    | "false" ^^ (_ => false)
  )

}

object ParseJSON extends JSON {

  import java.io.FileReader

  def main(args : Array[String]) : Unit =
    println(parseAll(value, new FileReader(args.head)))

}
