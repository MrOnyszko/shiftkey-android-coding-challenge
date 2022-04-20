package pl.onyszko.domain.useCase

interface UseCase<out T> {
  suspend fun execute(): T
}

interface ParamUseCase<out T, in P> {
  suspend fun execute(param: P): T
}