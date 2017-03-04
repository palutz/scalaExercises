//
//  defining functions
//
def partial1[A,B,C](a: A, f: (A, B) => C): B => C = {
  (b: B) => f(a, b)
}

def curry[A, B, C](f: (A, B) => C) : A => (B => C) = {
  (a: A) => {
    (b: B) => f(a, b)
  }
}

def uncurry[A, B, C](f: A => B => C): (A, B) => C = {
  (a: A, b : B) => f(a)(b)
}

// ... and using functions...
// CURRY
// with anonymous functions
def curried = curry((x: Int, y: Int) => x +  y)   // curried: curried[] => Int => (Int => Int)
def add1 = curried(1)(_: Int)   // add1: add1[] => Int => Int
// with functions
def mysum(x: Int, y: Int): Int = x + y  // mysum: mysum[](val x: Int,val y: Int) => Int
def curr2 = curry(mysum)  // curr2: curr2[] => Int => (Int => Int)

add1(2)   // 3

// UNCURRY
def uncurried = uncurry(curried)   // uncurried: uncurried[] => (Int, Int) => Int
def uncurried2 = uncurry(curr2)   // uncurried2: uncurried2[] => (Int, Int) => Int
def add1u = uncurried(1, _: Int)

add1u(2)  // 3
