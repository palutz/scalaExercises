val set = Set(1, 9, 10, 22)
val list = List(3, 4, 5, 10)

set ++ list
list ++ set
set.map(_ * 4)

val llist = List(List(1), List(2, 3, 4), List(5, 6, 7), List(8, 9, 10))
llist.flatten
llist.flatMap(_.map(_ * 4))

val alist = List(1,2,3,4,5,6)
alist.map(i => if(i%2 == 0) Some(i) else None) // res5: List[Option[Int]] = List(None, Some(2), None, Some(4), None, Some(6))
alist.map(i => if(i%2 == 0) Some(i) else None).flatten // res6: List[Int] = List(2, 4, 6)
alist.flatMap(i => if(i%2 == 0) Some(i) else None) // res7: List[Int] = List(2, 4, 6)

alist.collect {
  case x: Int if x % 3 == 0 => x * x   // partial function
}
// 2 partial functions
val pf1: PartialFunction[Int, Int] = { case x: Int if x % 2 == 0 => x * x }
val pf2: PartialFunction[Int, Int] = { case x: Int if x % 2 != 0 => x }

alist.collect{ pf1 orElse(pf2)}

val pf1o: PartialFunction[Int, Option[Int]] = { case x: Int if x % 2 == 0 => Some(x * x) }
val pf2o: PartialFunction[Int, Option[Int]] = { case x: Int if x % 2 != 0 => None }

alist.collect(pf1o orElse(pf2o))     // res10: List[Option[Int]] = List(None, Some(4), None, Some(16), None, Some(36))
alist.collect(pf1o orElse(pf2o)).flatten  // res11: List[Int] = List(4, 16, 36)

for {
  al <- alist.collect(pf1o orElse(pf2o))
  a <- al
} yield(a)      // res12: List[Int] = List(4, 16, 36)

val lt = List("Phoenix" → "Arizona", "Austin" → "Texas")
val rst = lt.toMap
for {
  lll <- lt
  m <- Map(lll._1.toLowerCase -> lll._2.toUpperCase)
} yield m


