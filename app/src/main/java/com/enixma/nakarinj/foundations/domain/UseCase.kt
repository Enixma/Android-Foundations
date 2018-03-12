package com.enixma.nakarinj.foundations.domain

import io.reactivex.Observable

interface UseCase<in P : UseCase.Request, Q : UseCase.Result> {

    fun execute(request: P): Observable<Q>

    interface Request

    interface Result
}
