package com.zmccoy


case class UserId(s: String)
case class User(id: UserId, name: String)

trait UserGatheringOptions {
  def getUserId(name: String): Option[UserId] =
    if(name == "Zach McCoy") Some(UserId("1111")) else None


  def getUser(id: UserId): Option[User] =
    if(id.s == "1111") Some(User(id, "Zach McCoy")) else None

  //We don't have any way of knowing what failed.
  def getUserByName(name: String): Option[User] =
    for {
      userId <- getUserId(name)
      user <- getUser(userId)
    } yield user
}

// We want to turn our failed computations into values that mean something.
