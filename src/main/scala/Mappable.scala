package com.zmccoy


trait Mappable[F[_]] {

  def map[A, B](fa: F[A])(f: A => B): F[B]

}

trait FlatMappable[F[_]] extends Mappable[F] {

  def flatmap[A,B](fa: F[A])(f: A => F[B]): F[B]
}
