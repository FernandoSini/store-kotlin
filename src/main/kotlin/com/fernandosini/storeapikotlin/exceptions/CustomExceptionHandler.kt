package com.fernandosini.storeapikotlin.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.ZonedDateTime

@Controller
@ControllerAdvice
class CustomExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(Exception::class)
    fun handleExceptions(exception: Exception, webRequest: WebRequest): ResponseEntity<Any> {
        val exceptionResponse = ExceptionResponse(
            ZonedDateTime.now(),
            exception.message,
            webRequest.getDescription(false)
        )

        return ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }
    
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(exception: Exception, webRequest: WebRequest): ResponseEntity<Any> {
        val exceptionResponse = ExceptionResponse(
            ZonedDateTime.now(),
            exception.message,
            webRequest.getDescription(false)
        )
        return ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(exception: Exception, webRequest: WebRequest): ResponseEntity<Any> {
        val exceptionResponse = ExceptionResponse(
            ZonedDateTime.now(),
            exception.message,
            webRequest.getDescription(false)
        )
        return ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ForbbidenException::class)
    fun handleForbbidenException(exception: Exception, webRequest: WebRequest): ResponseEntity<Any> {
        val exceptionResponse = ExceptionResponse(
            ZonedDateTime.now(),
            exception.message,
            webRequest.getDescription(false)
        )
        return ResponseEntity(exceptionResponse, HttpStatus.FORBIDDEN)
    }

    @ExceptionHandler(InternalServerErrorException::class)
    fun handleInternalServerException(exception: Exception, webRequest: WebRequest): ResponseEntity<Any> {
        val exceptionResponse = ExceptionResponse(
            ZonedDateTime.now(),
            exception.message,
            webRequest.getDescription(false)
        )
        return ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(UnauthorizedException::class)
    fun handleUnauthorizedException(exception: Exception, webRequest: WebRequest): ResponseEntity<Any> {
        val exceptionResponse = ExceptionResponse(
            ZonedDateTime.now(),
            exception.message,
            webRequest.getDescription(false)
        )
        return ResponseEntity(exceptionResponse, HttpStatus.UNAUTHORIZED)
    }
}