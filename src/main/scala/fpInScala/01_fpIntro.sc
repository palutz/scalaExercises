
def partial1[A, B, C](a: A, f: (A, B) => C): B => C = {
  (xb: B) => f(a, xb) // returning a function with signature (B= > C) using an anonyous funct.
  // with a parameter of type B and
}

////// 2.1 - recursive fibonacci
def fib(n: Int) : Long = {
  @annotation.tailrec
  def inner(prev: Long, curr: Long, idx: Int) : Long = {
    if(idx >= n) prev
    else
      inner(curr, curr + prev, idx + 1)
  }
  inner(0, 1, 1)
}

////// 2.1 - recursive fibonacci 2
def fib2(n: Int): Int = {
  @annotation.tailrec
  def inner(prev: Int, curr: Int, idx: Int) : Int = {
    if(idx <= 0) prev
    else
      inner(curr, prev + curr, idx - 1)
  }
  inner(0, 1, n)
}

////// 2.2 polymorphic isSorted (recursive)
def isSorted[A] (ar: Array[A], ordered: (A, A) => Boolean): Boolean = {
  def inner(idx: Int) : Boolean = {
    if(idx >= ar.length - 1) true
    else {
      if(ordered(ar(idx), ar(idx + 1))) inner(idx + 1)
      else false
    }
  }
  inner(0)
}

isSorted(Array(9, 7, 3), (a: Int, b: Int) => a > b)  // true
isSorted(Array(9, 11, 3), (a: Int, b: Int) => a > b) // false

isSorted(Array(1, 3, 5, 7), (x: Int, y: Int) => x < y)