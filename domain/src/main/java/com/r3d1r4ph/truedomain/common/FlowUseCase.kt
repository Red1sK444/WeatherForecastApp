package com.r3d1r4ph.truedomain.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

interface FlowUseCase<in Input, Output> {

	operator fun invoke(param: Input): Flow<Result<Output>> =
		execute(param)
			.catch { e -> emit(Result.failure(e)) }
			.flowOn(Dispatchers.IO)

	fun execute(param: Input): Flow<Result<Output>>
}
