package com.zmccoy

import scalaz._
import Scalaz._

/*
https://github.com/scalaz/scalaz/blob/f948c834faba1684d820f0f3d13d917298a2e26e/core/src/main/scala/scalaz/Either.scala
 */

trait UserGatheringDisjunctions {
  def getUserId(name: String): Option[UserId] =
    if(name == "Zach McCoy") Some(UserId("1111")) else None

  def getUser(id: UserId): Option[User] =
    if(id.s == "1111") Some(User(id, "Zach McCoy")) else None

  def getUserByName(name: String): String \/ User =
    for {
      userId <- getUserId(name) \/> s"Couldn't find user's name: ${name}"
      user <- getUser(userId) \/> s"Couldn't find user for user id: ${userId}"
    } yield user
}

//Using Disjunctions we've controlled our side effects and done our errors through values

//Disjunctions are a sum type.

//Remember flatmap is sequencing, which means that we only get back the first error that happened.  Since the first computation is used in the next.

/*
 scala> getUserByName("Zach")
res7: scalaz.\/[String,com.zmccoy.User] = -\/(Couldn't find user's name: Zach)

scala> getUserByName("Zach McCoy")
res8: scalaz.\/[String,com.zmccoy.User] = \/-(User(UserId(1111),Zach McCoy))
 */
