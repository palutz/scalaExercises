import fpIntro.second._

val ll: fpList[Int] = Cons(2, Cons(3, Cons(5, Nil)))
val ld : fpList[Double] = Cons(2.0, Cons(3.0, Cons(5.0, Nil)))

fpList.sum(ll)
fpList.product(ld)

//fpList(1,2,3,4,5) match {   // Int = 3
fpList(0,1,2,3,4,5) match {   // Int = 115
  case Cons(x, Cons(2, Cons(4, _))) => x
  case Nil => 42
  case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
  case Cons(h, t) => h + fpList.sum(t) + 100
  case _ => 101
}

fpList.drop(fpList(0,1,2,3,4,5), 2)