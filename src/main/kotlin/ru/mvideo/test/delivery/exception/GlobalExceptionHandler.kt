package ru.mvideo.test.delivery.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(DeliveryNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(ex: DeliveryNotFoundException): Map<String, String> {
        return mapOf("error" to (ex.message ?: "Not found"))
    }

    @ExceptionHandler(InvalidStatusTransitionException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleInvalidTransition(ex: InvalidStatusTransitionException): Map<String, String> {
        return mapOf("error" to (ex.message ?: "Bad Request"))
    }
}