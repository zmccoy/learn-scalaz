package com.zmccoy

import scala.concurrent.Future
import scalaz._
import Scalaz._
import scala.concurrent.ExecutionContext.Implicits.global //Required for implicit Functor[Future]

/*
https://github.com/scalaz/scalaz/blob/f948c834faba1684d820f0f3d13d917298a2e26e/core/src/main/scala/scalaz/OptionT.scala
*/

trait OptionTImpl extends FutureDB {

  def getUserByNameAndThen[B](name: String, f: User => B): Future[Option[B]] =
    (for {
      userId <- OptionT(getUserId(name))
      user <- OptionT(getUser(userId))
    } yield f(user)).run

}

/*
 case class OptionT[F[_], A](run: F[Option[A]])
 */
