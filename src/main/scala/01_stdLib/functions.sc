//// Write a function, factory, that takes a number as its parameter and returns another function.
//// The returned function should take an array of numbers as its parameter, and return an array of those numbers multiplied by the number that was passed into the first function.
//def factory(x: Int) = {
//  (a: Array[Int]) => {
//    a.map(_ * x)
//  }
//}
//def f5 = factory(5)
//f5(Array(1,2,3))  // res0: Array[Int] = Array(5, 10, 15)
//
//// curried version
//def factory2(x: Int)(arr: Array[Int]): Array[Int] = {
//  arr.map(_ * x)
//}
//def f52 = factory2(5)(_)
//f52(Array(1,2,3))  // res1: Array[Int] = Array(5, 10, 15)

//import scala.concurrent.Future
//import scala.concurrent.ExecutionContext.Implicits.global
//
//val ob : Future[Option[String]] = Future { Some("Steo")}
//
//def mymap(oob: Future[Option[String]]) : Future[Option[Int]] = {
//  oob.map( f => f.map(x => x.length))
//}

//def myflatMap(oob: Future[Option[String]]) : Future[Option[Int]] = {
//  Future {
//    oob.flatMap( f => {
//      f match {
//        case Some(a) => Some(a.length)
//        case _ => None
//      }
//    })
//  }
//}
//




// val t = gv.reduce(_ + _)
//val listValues = mapValues.values
// val a = grouped.reduce()
//val red = mapValues.values.reduceRight(_ + _)

// im.map((x => x zip (Stream from 1)).reverse.head)
