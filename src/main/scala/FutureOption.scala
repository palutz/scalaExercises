/**
  * Trying to deal with Future of Option ...
  */

import scala.concurrent.Future
import scala.concurrent.ExecutionContext

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