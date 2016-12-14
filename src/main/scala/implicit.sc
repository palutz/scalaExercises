abstract class SemiGroup[A] {
  def add(x: A, y: A) : A
}

abstract class Monoid[A] extends SemiGroup[A] {
  def unit: A
}


implicit object StringMonoid extends Monoid[String] {
  def add(x:String, y: String) = x concat y
  def unit: String = ""
}

implicit object IntMonoid extends Monoid[Int] {
  def add(x:Int, y:Int): Int = x + y
  def unit: Int = 0
}

def sum[A](aList: List[A])(implicit m: Monoid[A]): A = {
  aList match {
    case x :: xs => m.add(x, sum(xs))
    case _ => m.unit
  }
}

val li = List(1, 2, 3)
sum(li)
val ls = List("Hello", ", " , "World")
sum(ls)

// this would be error .. no implicit defined for booles
//val lb = (true, true, false)
//sum(lb)

class myWrapper(x: Int) {
  def tenTime = x * 10
}

implicit def noName(x: Int) = new myWrapper(x)

10.tenTime
val y = 1
y.tenTime

class myBooleanImp(v: Boolean) {
  def writeIt = v match {
    case true => "That's correct"
    case false => "Something is not right"
  }
}

implicit def noNameB(x: Boolean) = new myBooleanImp(x)

true.writeIt
