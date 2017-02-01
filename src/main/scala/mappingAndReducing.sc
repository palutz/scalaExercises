// val l = List(("uno", 1), ("uno", 3), ("uno", 2), ("uno", 1), ("uno", 1), ("due", 10), ("due", 2), ("due", 1), ("tre", 17))
val l = List("uno", "uno", "uno", "uno", "uno", "due", "due", "due", "tre")
val grouped = l.groupBy(x => x)   // // getting: Map(tre -> List(tre), due -> List(due, due, due), uno -> List(uno, uno, uno, uno, uno))
  .map(x => (x._1, x._2.length))   // Map(tre -> 1, due -> 3, uno -> 5)
  .toList.sortWith(_._2 > _._2)

val ll = List(("uno", 1), ("uno", 3), ("uno", 2), ("uno", 1), ("uno", 1), ("due", 10), ("due", 2), ("due", 1), ("tre", 17))
val gr = ll.groupBy(_._1)  // Map(tre -> List((tre,17)), due -> List((due,10), (due,2), (due,1)), uno -> List((uno,1), (uno,3), (uno,2), (uno,1), (uno,1)))
val grl = ll.groupBy(_._1).map {
  case (n, l) => (n, l.foldLeft(0){
    case (acc, (_, x)) => acc + x
  })
}   /// Map(tre -> 17, due -> 13, uno -> 8)

val gg = ll.groupBy(_._1).map(x => (x._1, x._2.unzip._2.sum))   // Map(tre -> 17, due -> 13, uno -> 8)

ll.groupBy(_._1).map{ case (name, list) => (name, list.unzip._2.sum)}   // Map(tre -> 17, due -> 13, uno -> 8)
