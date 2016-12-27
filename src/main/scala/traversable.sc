val array = Array(87, 44, 5, 4, 200, 10, 39, 100)

val oddAndSmallPartial: PartialFunction[Int, String] = {
  case x: Int if x % 2 != 0 && x < 100 ⇒ "Odd and less than 100"
}

val evenAndSmallPartial: PartialFunction[Int, String] = {
  case x: Int if x != 0 && x % 2 == 0 && x < 100 ⇒ "Even and less than 100"
}

val negativePartial: PartialFunction[Int, String] = {
  case x: Int if x < 0 ⇒ "Negative Number"
}

val largePartial: PartialFunction[Int, String] = {
  case x: Int if x > 99 ⇒ "Large Number"
}

val zeroPartial: PartialFunction[Int, String] = {
  case x: Int if x == 0 ⇒ "Zero"
}

val result = array groupBy {
  oddAndSmallPartial orElse
    evenAndSmallPartial orElse
    negativePartial orElse
    largePartial orElse
    zeroPartial
}
result("Even and less than 100") // res0: Array[Int] = Array(44, 4, 10)
(result("Large Number") size) // res1ç Int = 2

val mapoddAndSmallPartial: PartialFunction[Int, Map[String, Int]] = {
  case x: Int if x % 2 != 0 && x < 100 ⇒ Map("Odd and less than 100" -> x)
}
array.collect(mapoddAndSmallPartial)

val list = List(5, 4, 3, 2, 1)
// foldLeft
(0 /: list) { (acc, a) => acc - a }
(0 /: list) { _ - _ }
list.foldLeft(0) { (acc, a) => acc - a }
list.foldLeft(0) { _ - _ }
// foldRight
(list :\ 0) {(a, acc) => a - acc}
(list :\ 0) {_ - _}
list.foldRight(0) {(a, acc) => a - acc}
list.foldRight(0) {_ - _}

val intList = List(5, 4, 3, 2, 1)
intList.reduceLeft {_ + _} // 15
intList.reduceRight {_ + _} // 15

val stringList = List("Do", "Re", "Me", "Fa", "So", "La", "Te", "Do")
stringList.reduceLeft {_ + _} //  String = DoReMeFaSoLaTeDo
stringList.reduceRight {_ + _} //  String = DoReMeFaSoLaTeDo

val lt = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))
lt.transpose // List(List(1, 4, 7), List(2, 5, 8), List(3, 6, 9))
val lt2 = List(List(1), List(4))
lt2.transpose  // List(List(1, 4))

val stringBuilder = new StringBuilder()
val l = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
stringBuilder.append("I want all numbers 6-12: ")
l.filter(it ⇒ it > 5 && it < 13).addString(stringBuilder, ",")
stringBuilder.mkString   // I want all numbers 6-12: 6,7,8,9,10,11,12