package com.zmccoy

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait FutureDB {
  def getUserId(name: String): Future[Option[UserId]] = {
    if(name == "Zach McCoy")
      Future.successful(Some(UserId("1111")))
    else Future.successful(None)
  }

  def getUser(id: UserId): Future[Option[User]] = {
    if(id.s == "1111")
      Future.successful(Some(User(id, "Zach McCoy")))
    else Future.successful(None)
  }

}


  //You can't do this
  /*
   for {
     maybeUserId <- getUserId(name)
     userId <- maybeUserId
     maybeUser <- getUser(userId)
     user <- maybeUser
   } yield user
   */

  //Monads don't compose.

  //Unleash the horrors
  //You've written code like this.

  //Not everything has a default.
  //This is not sane.
  //You are not sane.

trait FutureDBImpl extends FutureDB {

  def getUserByNameAndThen[B](name: String, f: User => B): Future[Option[Future[Option[B]]]] = {
    getUserId(name) map { maybeUserId =>
      maybeUserId map { userId =>
        getUser(userId) map { maybeUser =>
          maybeUser map { user =>
            f(user)
          }
        }
      }
    }
  }

}
