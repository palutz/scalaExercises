val a = Array("a", "b", "c", "d", "e", "f", "g", "h", "i", "j")

val c = a.toList zip (Stream from 6)

// val ar = Array(3,5,13,1,21,6,9,2)
val ar = c.toArray

val ars = ar.sortWith(_._2 > _._2)

val x = 10
val nar = Array.concat(
  ars filter (x < _._2),
  ars.filter(x == _._2),
  Array(("z", x)),
  ars.filter(x > _._2)).take(10)

