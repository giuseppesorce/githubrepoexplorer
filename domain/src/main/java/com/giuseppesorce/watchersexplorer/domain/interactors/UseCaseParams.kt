package com.giuseppesorce.watchersexplorer.domain.interactors

import java.lang.Exception

/**
 * @author Giuseppe Sorce
 */
interface UseCaseParams

/**
 * Error class which represents the Error returned by the [UseCase] if no parameters are supplied.
 */
data class MissingParamsError(override val message: String) : Exception(message)