package com.zmccoy

import scala.concurrent.Future
import scalaz._
import Scalaz._
import scala.concurrent.ExecutionContext.Implicits.global


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
