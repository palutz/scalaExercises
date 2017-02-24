package fpIntro.second {

  sealed trait fpList[+A]
  case object Nil extends fpList[Nothing]
  case class Cons[+A](head: A, tail: fpList[A]) extends fpList[A] {
    override def toString: String = {
      def inner[A](l : fpList[A], ls: List[String]) : List[String] = {
        l match {
          case Cons(x, xs) => inner(xs, ls :+ x.toString)
          case Nil => ls
        }
      }
      "List(" + inner(this, List()).mkString(",") + ")"
    }
  }

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

    def foldRight[A, B](al: fpList[A], z: B)(f: (A, B) => B): B = {
      al match {
        case Nil => z
        case Cons(x, xs) => f(x, foldRight(xs, z)(f))   /// NOT tailrec!
      }
    }

    def sum2(ll: fpList[Int]): Int = {
      // fpList.foldRight(ll, 0)((x,y) => x + y)  .. same of
      fpList.foldRight(ll, 0)(_ + _)
    }

    def product2(ll: fpList[Double]): Double = {
      fpList.foldRight(ll, 1.0)(_ * _)
    }

    // simple my implementation of tail
    def tail[A](l: fpList[A]): fpList[A] = {
      l match {
        case Cons(_, xs) => xs
        case _ => Nil

      }
    }

    // SetHead - substitute the head of a list
    def setHead[A](a: A, l: fpList[A]): fpList[A] = {
      l match {
        case Cons(_, t) => Cons(a, t)
        case _ => Nil // Error
      }
    }

    def drop[A](l : fpList[A], n : Int): fpList[A] = {
      l match {
        case Cons(_, xs) if n > 0 => drop(xs, n - 1)
        case Nil => Nil
        case _ if n <= 0 => l
      }
    }

    def dropWhile[A](l: fpList[A], f: A => Boolean) : fpList[A] = {
      l match {
        case Cons(x, xs) if f(x) => dropWhile(xs, f)
        case _ => l
      }
    }
    // curried version to help Scala to infer better the type of A in the function f
    def dropWhile2[A](l: fpList[A])(f: A => Boolean): fpList[A] = {
      dropWhile(l, f)
    }

    def append[A](l1: fpList[A], l2: fpList[A]) : fpList[A] = {
      l1 match {
        case Cons(x, xs) => Cons(x, append(xs, l2))
        case Nil => l2
      }
    }

    // return a list with all the element but the last one ...
    def init[A](l: fpList[A]): fpList[A] = {
      l match {
        case Cons(_, Nil) => Nil
        case Cons(x, xs) => Cons(x, init(xs))
        case Nil => Nil
      }
    }

    def apply[A](al: A*): fpList[A] = {
      if (al.isEmpty) Nil
      else Cons(al.head, apply(al.tail: _*))
    }
  }
}