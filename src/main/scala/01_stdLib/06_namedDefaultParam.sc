def myreduce(a: Int)(f:(Int, Int) => Int): Int = f(a, a)
val reduce5 = myreduce(5)_  // reduce5: ((Int, Int) => Int) => Int = <function1>
reduce5(_+_)  // 10
reduce5(_*_)  // 25
// or I can use default parameter also for the func
def myreduce2(a: Int, f:(Int, Int) => Int = _ + _): Int = f(a, a)
myreduce2(5)  // 10
myreduce2(5, _*_)  // 25
myreduce2(6, { case (x, y) if x % 2 == 0 => (x + x) * x }) // 72
myreduce2(5, { case (x, y) if x % 2 == 0 => (x + x) * x }) // match error
