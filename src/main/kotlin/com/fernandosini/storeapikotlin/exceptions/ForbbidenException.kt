package com.fernandosini.storeapikotlin.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.FORBIDDEN)
class ForbbidenException(exception: String) : RuntimeException(exception) {
}