package fpIntro.second {

  sealed trait fpList[+A]
  case object Nil extends fpList[Nothing]
  case class Cons[+A](head: A, tail: fpList[A]) extends fpList[A]

  // companion object for my List (fpList)
  object fpList {
    // (book) not tailrec version ...
    //  def sum(ll : fpList[Int]) : Int = {
    //    ll match {
    //      case Nil => 0
    //      case Cons(x, xs) => x + sum(xs)
    //    }
    //  }

    // tailrec version...
    def sum(ll: fpList[Int]): Int = {
      @annotation.tailrec
      def inner(inL: fpList[Int], acc: Int): Int = {
        inL match {
          case Nil => acc
          case Cons(x, xs) => inner(xs, acc + x)
        }
      }

      inner(ll, 0)
    }

    // (book) not tailrec
    //  def product(ll : fpList[Double]) : Double = {
    //    ll match {
    //      case Nil => 1.0
    //      case Cons(0.0, _) => 0.0
    //      case Cons(x, xs) => x * product(xs)
    //    }
    //  }

    def product(ll: fpList[Double]): Double = {
      @annotation.tailrec
      def inner(inL: fpList[Double], acc: Double): Double = {
        inL match {
          case Nil => acc
          case Cons(0.0, _) => 0.0
          case Cons(x, xs) => inner(xs, acc * x)
        }
      }

      inner(ll, 1.0)
    }


    def apply[A](al: A*): fpList[A] = {
      if (al.isEmpty) Nil
      else Cons(al.head, apply(al.tail: _*))
    }
  }
}