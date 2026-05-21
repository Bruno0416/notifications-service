package com.mariluz.notifications.exceptions;

import com.mariluz.notifications.dto.ErrorResponse;
import com.resend.core.exception.ResendException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handler error resend
    @ExceptionHandler(ResendException.class)
    public ResponseEntity<ErrorResponse> handleResendException(
        ResendException ex,
        HttpServletRequest request
    ) {
        Map<String, String> errors = Map.of(
            "error",
            "Error interno al enviar email",
            "detail",
            ex.getMessage() != null ? ex.getMessage() : "sin detalles"
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            ErrorResponse.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Error al enviar correo")
                .errors(errors)
                .endpoint(request.getRequestURI())
                .build()
        );
    }

    // Handler errores de validacion de campos (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
        MethodArgumentNotValidException ex,
        HttpServletRequest request
    ) {
        Map<String, String> errors = new LinkedHashMap<>();
        ex.getBindingResult()
            .getFieldErrors()
            .forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
            );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorResponse.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Error de validacion en los campos")
                .errors(errors)
                .endpoint(request.getRequestURI())
                .build()
        );
    }

    // Handler JSON malformado o tipo de dato incorrecto en el body
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleMessageNotReadable(
        HttpMessageNotReadableException ex,
        HttpServletRequest request
    ) {
        Map<String, String> errors = Map.of(
            "error",
            "El cuerpo de la peticion no es valido o esta malformado"
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorResponse.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Peticion invalida")
                .errors(errors)
                .endpoint(request.getRequestURI())
                .build()
        );
    }

    // Handler generico para excepciones no contempladas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
        Exception ex,
        HttpServletRequest request
    ) {
        Map<String, String> errors = Map.of(
            "error",
            "Error inesperado en el servidor",
            "detail",
            ex.getMessage() != null ? ex.getMessage() : "sin detalles"
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            ErrorResponse.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Error interno del servidor")
                .errors(errors)
                .endpoint(request.getRequestURI())
                .build()
        );
    }
}
