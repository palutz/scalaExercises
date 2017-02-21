import fpIntro.second._

val ll: fpList[Int] = Cons(2, Cons(3, Cons(5, Nil)))
val ld : fpList[Double] = Cons(2.0, Cons(3.0, Cons(5.0, Nil)))

fpList.sum(ll)
fpList.product(ld)