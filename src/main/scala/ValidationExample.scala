package com.zmccoy

import scalaz._
import Scalaz._

trait Validations {

  sealed trait Error
  case object NotGreaterThanThree extends Error
  case object DoesNotHaveAnA extends Error

  def lengthGreaterThan3(s: String): ValidationNel[Error, String] =
    if(s.length > 3) s.success else NotGreaterThanThree.failureNel

  def hasAnA(s: String): ValidationNel[Error, String] =
    if(s.toLowerCase.contains('a')) s.success else DoesNotHaveAnA.failureNel

  def checkBoth(s: String): ValidationNel[Error, String] =
    (lengthGreaterThan3(s) |@| hasAnA(s)) { (x,y) => x + y }

  /*
   scala> checkBoth("ct")
res4: scalaz.ValidationNel[x.Error,String] = Failure(NonEmptyList(NotGreaterThanThree, DoesNotHaveAnA))

scala> checkBoth("cat")
res5: scalaz.ValidationNel[x.Error,String] = Failure(NonEmptyList(NotGreaterThanThree))

scala> checkBoth("cats")
res6: scalaz.ValidationNel[x.Error,String] = Success(catscats)
   */

}
