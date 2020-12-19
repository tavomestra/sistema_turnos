package com.ingmega.turnos.advice;

import com.ingmega.turnos.exception.BadRequestException;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.ingmega.turnos.exception.ErrorRq;
        

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorRq> getGeneralException(Exception e) {
        log.error(e.getMessage(), e);
        ErrorRq errorRq = ErrorRq.getErrorRq(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorRq, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ErrorRq> getBadRequestException(BadRequestException e) {
        log.info(e.getErrorDto().getMessage());
        return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            org.springframework.web.bind.MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder errorMessage = new StringBuilder();

        if (fieldErrors != null && !fieldErrors.isEmpty()) {
            errorMessage.append(fieldErrors.get(0).getDefaultMessage());
        } else {
            errorMessage.append("Ocurrio un error al procesar la solicitud. Por favor verifique e intente de nuevo.");
        }

        ErrorRq errorInfo = ErrorRq.getErrorRq(HttpStatus.BAD_REQUEST.getReasonPhrase(), errorMessage.toString(),
                HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }
}
