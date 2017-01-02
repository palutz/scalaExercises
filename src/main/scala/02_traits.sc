// Same Monoid implementation but with traits this time

trait SemiGroup[A] {
  def add(x: A, y: A) : A
}

trait Monoid[A] extends SemiGroup[A] {
  def unit: A
}
//trait Monoid[A] { def unit: A }
//implicit object StringMonoid extends SemiGroup[String] with Monoid[String]

implicit object StringMonoid extends Monoid[String] {
  def add(x: String, y: String): String = x concat y
  def unit: String = ""
}

implicit object IntMonoid extends Monoid[Int] {
  def add(x: Int, y: Int): Int = x + y
  def unit: Int = 0
}

def addition[A](l: List[A])(implicit m: Monoid[A]): A = {
  l match {
    case x :: xs => m.add(x, addition(xs))
    case _ => m.unit
  }
}


var li = List(1, 2, 3)
addition(li)
val name = "Stefano"
var ls = List("Hi,", " ", "my ", "name ", "is ", name)
addition(ls)
