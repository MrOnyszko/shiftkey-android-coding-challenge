package pl.onyszko.remote.mapper

interface Mapper<in T, out R> {
  fun map(value: T): R
}

interface ArgMapper<in T, out R, in A> {
  fun map(value: T, arg: A): R
}