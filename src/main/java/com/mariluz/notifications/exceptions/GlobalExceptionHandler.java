package com.mariluz.notifications.exceptions;

import com.mariluz.notifications.dto.ErrorResponse;
import com.resend.core.exception.ResendException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        Map<String, String> error = Map.of(
            "error",
            "Error interno al enviar email"
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            ErrorResponse.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Error al enviar correo")
                .errors(error)
                .build()
        );
    }
}
