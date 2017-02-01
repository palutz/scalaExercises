/**
  * Trying to deal with Future of Option ...
  */

import scala.concurrent.Future
import scala.concurrent.ExecutionContext

// Let's abstract it with a trait.
// We are using only 3 feature of Future:
// - Map
// - FlatMap
// - create a new one
//
// ... but this is a MONAD (only differnce Monad is general, while our traits was internal at one class type,
// with the monad we need to pass both

trait Monad[M[_]] {
  def map[A, B](ma: M[A], f: A => B): M[B]
  def flatMap[A, B](ma: M[B], f: A => M[B]): M[B]
  def create[A](a: A): M[A]
}


trait aTraitForFuture {
  def map[A]
}

case class FutureOption[A](inner: Future[Option[A]])(implicit xc: ExecutionContext = ExecutionContext.global) {
  // Map for FutureOption
  def map[B](f: A => B): FutureOption[B] = FutureOption {
    inner.map{ _.map { f }}
  }

  def flatMap[B](f: A => FutureOption[B]): FutureOption[B] =
    FutureOption {
      inner.flatMap {
        case Some(a) => f(a).inner
        case _ => Future.successful(None)
      }
    }
}