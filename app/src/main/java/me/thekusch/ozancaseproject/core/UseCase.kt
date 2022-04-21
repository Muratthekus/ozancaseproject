package me.thekusch.ozancaseproject.core

interface UseCase {

    interface RequestUseCase<P : Params, T : Any?> : UseCase {
        suspend fun execute(params: P?): T
    }

    interface RetrieveUseCase<T : Any?> : UseCase {
        suspend fun execute(): T?
    }

    abstract class Params
}
