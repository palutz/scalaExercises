import fpIntro.second._

val ll: fpList[Int] = Cons(2, Cons(3, Cons(5, Nil)))
val ld : fpList[Double] = Cons(2.0, Cons(3.0, Cons(5.0, Nil)))

fpList.sum(ll)  // Int = 10
fpList.product(ld) // Double = 30.0

//fpList(1,2,3,4,5) match {   // Int = 3
fpList(0,1,2,3,4,5) match {   // Int = 115
  case Cons(x, Cons(2, Cons(4, _))) => x
  case Nil => 42
  case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
  case Cons(h, t) => h + fpList.sum(t) + 100
  case _ => 101
}

fpList.setHead(1, Nil)  // res3: fpIntro.second.fpList[Int] = Nil
fpList.setHead(1, fpList(0,1,2,3,4,5))   // res4: fpIntro.second.fpList[Int] = Cons(1,Cons(1,Cons(2,Cons(3,Cons(4,Cons(5,Nil))))))
fpList.drop(fpList(0,1,2,3,4,5), 2)   // res5: fpIntro.second.fpList[Int] = Cons(2,Cons(3,Cons(4,Cons(5,Nil))))
fpList.drop(Nil, 2)    // res6: fpIntro.second.fpList[Nothing] = Nil
fpList.dropWhile(fpList(0,1,2,3,4,5), (x : Int) => x < 3 )  //  res7: fpIntro.second.fpList[Int] = Cons(3,Cons(4,Cons(5,Nil)))
// dropwhile2 doesn't need to specify the type of x in the anonymous function
fpList.dropWhile2(fpList(0,1,2,3,4,5))(x => x < 3 )  //  res7: fpIntro.second.fpList[Int] = Cons(3,Cons(4,Cons(5,Nil)))

fpList.append(Cons(1, Cons(2, Cons(3, Nil))), Cons(4, Cons(5, Cons(6, Nil))))

fpList.init(fpList(0,1,2,3,4,5))
fpList.init(fpList(0))
fpList.init(Nil)

fpList.sum2(fpList(0,1,2,3,4,5))
fpList.product2(fpList(1,2,3,4,5))

//al match {
//  case Nil => z
//  case Cons(x, xs) => f(x, foldRight(xs, z)(f))   /// NOT tailrec!
//}
fpList.foldRight(fpList(1, 2, 3), Nil: fpList[Int])(Cons(_, _))
Cons(1, fpList.foldRight(fpList(2, 3), Nil: fpList[Int])(Cons(_, _)))
Cons(1, Cons(2, fpList.foldRight(fpList(3), Nil: fpList[Int])(Cons(_, _))))
Cons(1, Cons(2, Cons(3, fpList.foldRight(fpList(), Nil: fpList[Int])(Cons(_, _)) )))

def foldRight[A, B](z: B)(l : List[A])(f: (A, B) => B): B = {
  l match {
    case x :: xs => f(x, foldRight(z)(xs)(f))
    case _ => z
  }
}