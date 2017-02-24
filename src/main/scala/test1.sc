// 1
val s = "Az$6"
s.filter(x => x.isDigit).length
s.filter(x => x.isLetter).length

//2
val l = Range(1, 10).toList
l.filter(x => (x%3 == 0) || (x%5==0)).sum

(for {
  x <- l
  if(x%3 == 0) || (x%5==0)
}yield x).sum

// 3
val str = "60 54 546 548"
val ar = str.split(" ")
// just use string comparison...
val r1 = ar.sorted.reverse  // Array[String] = Array(60, 548, 546, 54)

// grouping by first char and sort the grouped values
val rl = ar.groupBy(x => x(0)).map(x => (x._1, x._2.sortWith(_ > _).mkString(""))).toList  // List((5,54854654), (6,60))
// sort the grouped values with a descending first char and then merge together
val res = rl.sortWith(_._1 > _._1).    // List((6,60), (5,54854654))
        map(_._2).mkString("")    // String = 6054854654
