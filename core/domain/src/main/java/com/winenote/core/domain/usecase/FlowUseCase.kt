package com.winenote.core.domain.usecase

import com.winenote.core.domain.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in Params, Type>(private val coroutineDispatcher: CoroutineDispatcher) {

    operator fun invoke(params: Params): Flow<Type> {
        return execute(params).flowOn(coroutineDispatcher)
    }

    abstract fun execute(params: Params) : Flow<Type>
}