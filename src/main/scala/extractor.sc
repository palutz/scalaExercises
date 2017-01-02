class Car(val make: String, val model: String, val year: Short, val topSpeed: Short)
object ChopShop {
  def unapply(x: Car) = Some(x.make, x.model, x.year, x.topSpeed)
}
val x = new Car("Chevy", "Camaro", 1978, 120) match {
  case ChopShop(s, t, _, _) ⇒ (s, t)
  case _ ⇒ ("Ford", "Edsel")
} // x: (String, String) = (Chevy,Camaro)

class Employee(val firstName: String, val middleName: Option[String], val lastName: String)
object Employee { //factory methods, extractors, apply
  //Extractor: Create tokens that represent your object
  def unapply(x: Employee) = Some(x.lastName, x.middleName, x.firstName)
}
val steo = new Employee("Stefano", Some("palutz"), "Paluello") // steo: Employee = Employee@7bc50f01
steo match {
  case Employee(l, m, f) => s"Employee name: $f ${m.getOrElse("")} $l"
  case _ => "none"
} // res0: String = Employee name: Stefano palutz Paluello
