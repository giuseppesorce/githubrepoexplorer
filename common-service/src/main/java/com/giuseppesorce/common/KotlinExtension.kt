package com.giuseppesorce.common

/**
 * @author Giuseppe Sorce
 */
infix fun <T : Any> Boolean.then(value: T): T? = if (this) value else null
