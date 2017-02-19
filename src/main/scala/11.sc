//val Match = "\\p{P}(?=\\s|$)".r
//val q = Match.replaceAllIn("A little poem. Vediamocome, funzione o forse no", "")

def findNumberinString(s: String): List[Int] = {
  val p = "([A-Za-z])".r
  // replace all characters with spaces and then convert the remaining digits in proper numbers
  p.replaceAllIn(s, " ").split(" ").
    map(x => if (x.trim.length > 0) Some(x.toInt) else None).toList.
    collect { case Some(x) => x.toInt }
}

def substituteNumber(s: String, ns: List[Int]) : String = {
  ns match {
    case x :: xs => {
      val ns = "?" * x
      substituteNumber(s.replace(x.toString, ns), xs)
    }
    case _ => s
  }
}

def subStr(str: String, ids: List[Int]): List[String] = {
  def inner(idx: List[Int], acc: List[String]): List[String] = {
    idx match {
      case x :: xs => {
        val dd = str.substring(0, x)
        println(dd)
        inner(xs, acc :+ dd)
      }
      case _ => acc
    }
  }
  inner(0 +: ids, List())
}

def normaliseStr(xs1: String, xs2: String) = {
  val ws = (0 to xs1.length).filter(xs1.startsWith("?", _))
  val ws2 = (0 to xs2.length).filter(xs2.startsWith("?", _))
  val vv = (ws ++ ws2).toSet

  (subStr(xs1, vv.toList).mkString(""), subStr(xs2, vv.toList).mkString(""))
}

val n1 = for {
  str <- "A2le 1p2e a10 10a 10".split(" ")
} yield substituteNumber(str, findNumberinString(str))

val n2 = for {
  str <- "Appl1 1pase a10 10a 10".split(" ")
} yield substituteNumber(str, findNumberinString(str))

val nn = n1 zip n2

normaliseStr("A??le", "?pple")

//for {
//  n12<- nn
//} yield(normaliseStr(n12._1), normaliseStr(n12._2))


// (List(1,2,3) ++ List(2,4,5)).toSet

//val n = 12
//val x= "?" * n
//

//ss.replaceFirst(n.toString, x)