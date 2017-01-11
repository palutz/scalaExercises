// Write a function, factory, that takes a number as its parameter and returns another function.
// The returned function should take an array of numbers as its parameter, and return an array of those numbers multiplied by the number that was passed into the first function.
def factory(x: Int) = {
  (a: Array[Int]) => {
    a.map(_ * x)
  }
}
def f5 = factory(5)
f5(Array(1,2,3))  // res0: Array[Int] = Array(5, 10, 15)

// curried version
def factory2(x: Int)(arr: Array[Int]): Array[Int] = {
  arr.map(_ * x)
}
def f52 = factory2(5)(_)
f52(Array(1,2,3))  // res1: Array[Int] = Array(5, 10, 15)