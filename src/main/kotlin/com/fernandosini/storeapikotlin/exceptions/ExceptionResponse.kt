package com.fernandosini.storeapikotlin.exceptions

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.io.Serializable
import java.time.ZonedDateTime


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class ExceptionResponse(
    private val timestamp: ZonedDateTime,
    private val message: String?,
    private val details: String?
) : Serializable {


}