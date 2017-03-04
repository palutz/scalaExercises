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
isSorted(Array(3, 7, 9), (a: Int, b: Int) => a > b)  // false
isSorted(Array(9, 11, 3), (a: Int, b: Int) => a > b) // false

isSorted(Array(1, 3, 5, 7), (x: Int, y: Int) => x < y) //true

//
// partial and currying
//
def partial1[A, B, C](a: A, f: (A, B) => C): B => C = {
  (xb: B) => f(a, xb) // returning a function with signature (B= > C) using an anonyous funct.
  // with a parameter of type B and
}

def currying1[A, B, C](f: (A, B) => C): A => (B => C) = {
  (a: A) =>
    (b: B) => f(a, b)
}

def f(a: Int, b: Int): Int = a + b
def strsLen(a: String, b: Boolean): Int = {
  if (b) a.length
  else 0
}

def add2 = partial1(2, f)
add2(1)
def shouldICheck = partial1("my string", strsLen) // shouldICheck[] => Boolean => Int
shouldICheck(true) // Int = 9
def should2 = partial1(_ : String, strsLen) // should2[] => String => Boolean => Int
should2("cippa")(false) // Int = 0

def addx = currying1(f)   // Int => (Int => Int)
def add1 = currying1(f)(1)   // Int => Int
// same as ..
def incrementBy1 = addx(1)  // Int => Int

def checkStr = currying1(strsLen) // String => (Boolean => Int)
checkStr("a string") // Boolean => Int
checkStr("a string")(true) // Int = 8

//
// uncurrying ...
//
// def uncurry[A,B,C](f: A => B => C): (A, B) => C = { the same of..
def uncurry[A,B,C](f: A => (B => C)): (A, B) => C = {
  (a: A, b: B) => f(a)(b)
}

def adding = uncurry(addx)  // (Int, Int) => Int
adding(10, 20) // Int = 30

//
// compose ...
//
def compose[A, B, C](f: B => C, g: A => B): A => C = {
  (a: A) => f(g(a))
}
def f(b: Int): Int = b / 2
def g(a: Int): Int = a + 2
compose(f, g)(0)  // Int = 1
compose(g, f)(0)  // Int = 2
