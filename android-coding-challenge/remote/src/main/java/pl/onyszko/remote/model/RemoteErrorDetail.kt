@file:Suppress("unused")

package pl.onyszko.remote.model

data class RemoteErrorDetail(
  val code: Int
) {
  constructor() : this(0)
}
