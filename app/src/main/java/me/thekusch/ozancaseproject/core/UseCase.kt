package me.thekusch.ozancaseproject.core

interface UseCase {

    interface RequestUseCase<P : Params, T : Any?> : UseCase {
        suspend fun execute(params: P?): Resource<T>
    }

    interface RetrieveUseCase<T : Any?> : UseCase {
        suspend fun execute(): Resource<T?>
    }

    abstract class Params
}
