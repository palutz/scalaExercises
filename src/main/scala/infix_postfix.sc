//
//OPERATORS
//
class myClass(a: Int) {
  def func1(b: Int): Int = a + b
}
val aC = new myClass(3)
aC func1 10
aC.func1(6)

class myPost(a: Int) {
  def unary_+ = a.toString concat  " at "
  def unary_! = a * a
}
val aa = new myPost(3)
+aa
!aa
//
// INFIX TYPES
// An infix type T1 op T2 consists of an infix operator op which gets
// applied to two type operands T1 and T2. The type is equivalent to the
// type application op[T1,T2].
case class Person(name: String) {
  def loves(person: Person) = new Loves(this, person)
}
class Loves[A, B](val a: A, val b: B)

def announceCouple(couple: Person Loves Person) = {
  //Notice our type: Person loves Person!
  couple.a.name + " is in love with " + couple.b.name
}
val romeo = new Person("Romeo")
val juliet = new Person("Juliet")

announceCouple(romeo loves juliet)


