val l = List(1,2,3)
val l2 = List(4,5,6)

// for sintactic sugar for flatmap and map..
for {
  x <- l
  y <- l2
} yield (x * y)  // res1: List[Int] = List(4, 5, 6, 8, 10, 12, 12, 15, 18)

// 2 maps won't get the same result.. but a List of Lists
l.map { x => l2.map { y => x * y }} // res2: List[List[Int]] = List(List(4, 5, 6), List(8, 10, 12), List(12, 15, 18))
// instead with flatMap we got the same result of the for back...
l flatMap { x => l2 map { y => x * y }}  // res1: List[Int] = List(4, 5, 6, 8, 10, 12, 12, 15, 18)
(l.map(x => l2.map (y => x * y ))).flatten // same result but is better to replace map and flatten with flatMap

// with 3 lists... nested flatMap(s) and map becoming a bit unreadable...
val l3 = List(10,20)
for {
  i <- l
  i2 <- l2
  x <- l3
} yield (i * i2 + x)  // res5: List[Int] = List(14, 24, 15, 25, 16, 26, 18, 28, 20, 30, 22, 32, 22, 32, 25, 35, 28, 38)
// or I can write it like this...
l.flatMap(i =>
  l2.flatMap(i2 =>
    l3.map(x => i * i2 + x)))

l flatMap { i =>
    l2 flatMap { i2 =>
      l3 map { x => i * i2 + x }
    }
}  // res6: List[Int] = List(14, 24, 15, 25, 16, 26, 18, 28, 20, 30, 22, 32, 22, 32, 25, 35, 28, 38)


val list = List("India", "Japan", "France", "Russia")
val capitals =
  Map("India" -> "New Delhi", "Japan" -> "Tokyo", "France" -> "Paris")

for {
  c <- list
  j <- capitals get c orElse None
} yield c -> j
// or ..
list flatMap { c =>
  capitals.get(c).orElse(None) map { j =>
    c -> j
  }
}