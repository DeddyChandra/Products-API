package bootcamp.dc.kotlin.restful.api.controller

import bootcamp.dc.kotlin.restful.api.error.NotFoundException
import bootcamp.dc.kotlin.restful.api.error.UnauthorizedException
import bootcamp.dc.kotlin.restful.api.model.WebResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun validationHandler(constraintViolationException: ConstraintViolationException): WebResponse<String> {
        return WebResponse(
            code = 400,
            status = "BAD REQUEST",
            data = constraintViolationException.message!!
        )
    }
    @ExceptionHandler(value = [NotFoundException::class])
    fun notFound(notFoundException: NotFoundException): WebResponse<String> {
        return WebResponse(
            code = 404,
            status = "Item Not Found!",
            data = "Item Not Found!!! "
        )
    }

    @ExceptionHandler(value = [UnauthorizedException::class])
    fun unauthorized(unauthorizedException: UnauthorizedException): WebResponse<String> {
        return WebResponse(
            code = 401,
            status = "Unauthorized",
            data = "Please insert your X-Api-Key"
        )
    }
}