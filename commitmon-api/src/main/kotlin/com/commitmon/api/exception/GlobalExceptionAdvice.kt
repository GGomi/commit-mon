package com.commitmon.api.exception

import com.commitmon.api.logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@RestControllerAdvice
class GlobalExceptionAdvice {

    private val log by logger()

    /**
     * 아래 정의되지 않은 모든 예외를 서버 예외로 처리한다
     */
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ErrorResponse> {
        log.error("handleException", e)
        val response = ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR)
        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    /**
     * javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다.
     * HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생
     * 주로 @RequestBody, @RequestParam 어노테이션에서 발생
     */
//    @ExceptionHandler(MethodArgumentNotValidException::class)
//    protected fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
//        log.error("MethodArgumentNotValidException", e)
//        val response = ErrorResponse(ErrorCode.INVALID_INPUT_VALUE, e.bindingResult)
//        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
//    }

    /**
     * @ModelAttribute 으로 binding error 발생시 BindException 발생한다.
     * ref https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-modelattrib-method-args
     */
//    @ExceptionHandler(BindException::class)
//    protected fun handleBindException(e: BindException): ResponseEntity<ErrorResponse> {
//        log.error("BindException", e)
//        val response = ErrorResponse(ErrorCode.INVALID_INPUT_VALUE, e.bindingResult)
//        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
//    }

    /**
     * enum type 일치하지 않아 binding 못할 경우 발생
     * 주로 @RequestParam enum으로 binding 못했을 경우 발생
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    protected fun handleMethodArgumentTypeMismatchException(e: MethodArgumentTypeMismatchException): ResponseEntity<ErrorResponse> {
        log.error("MethodArgumentTypeMismatchException", e)
        val response = ErrorResponse(ErrorCode.INVALID_TYPE_VALUE, e)
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    protected fun handleHttpRequestMethodNotSupportedException(e: HttpRequestMethodNotSupportedException?): ResponseEntity<ErrorResponse> {
        log.error("HttpRequestMethodNotSupportedException", e)
        val response = ErrorResponse(ErrorCode.METHOD_NOT_ALLOWED)
        return ResponseEntity(response, HttpStatus.METHOD_NOT_ALLOWED)
    }

    // Authentication 객체가 필요한 권한을 보유하지 않은 경우 발생합
    @ExceptionHandler(AccessDeniedException::class)
    protected fun handleAccessDeniedException(e: AccessDeniedException?): ResponseEntity<ErrorResponse> {
        log.error("AccessDeniedException", e)
        val response = ErrorResponse(ErrorCode.HANDLE_ACCESS_DENIED)
        return ResponseEntity(response, HttpStatus.valueOf(ErrorCode.HANDLE_ACCESS_DENIED.status))
    }

    /**
     * 자바, 스프링 및 내부적인 예외 값들에대한 validation, 아래 해당 exception은 세분화 하지 않고 하나로 퉁침 -> 세밀하게 가져갈 필요가 있는경우 분리해도 됨
     * HttpMessageNotReadableException: json serialize 못하는 하는 경우, json 포멧 자체가 안맞을 때 발생
     * MissingServletRequestParameterException: 필수 request parameter 없는 경우
     * IllegalArgumentException: java exception
     */
    @ExceptionHandler(
        IllegalArgumentException::class,
        MissingServletRequestParameterException::class,
        HttpMessageNotReadableException::class
    )
    protected fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        log.error("IllegalArgumentException", e)
        val response = ErrorResponse(ErrorCode.INVALID_INPUT_VALUE, e)
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    // 비지니스 로직에 의한 Exception
    /**
     * BusinessException 상속하는 ERP Exception(비지니스 코드 관련 예외)처리를 담당한다.
     * Error Response의 message, status 값은 ErrorCode 값에 의해 결정된다.
     */
    @ExceptionHandler(BusinessException::class)
    protected fun handleBusinessException(e: BusinessException): ResponseEntity<ErrorResponse> {
        log.error("BusinessException", e)
        val response = ErrorResponse(e.code, e)
        return ResponseEntity(response, HttpStatus.valueOf(e.code.status))
    }
}