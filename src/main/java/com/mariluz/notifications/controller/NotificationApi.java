package com.mariluz.notifications.controller;

import com.mariluz.notifications.dto.ErrorResponse;
import com.mariluz.notifications.dto.PurchaseRequest;
import com.mariluz.notifications.dto.ReservationRequest;
import com.resend.core.exception.ResendException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface NotificationApi {
    // Reservas
    @Operation(
        summary = "Envio correo de reserva",
        description = "Envía un correo de confirmación de reserva."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Correo enviado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = String.class),
                examples = @ExampleObject(
                    value = """
                    {
                    "Correo de confirmación enviado."
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Error de validación en los campos enviados (ej. email inválido, titulo vacio, body vacio).",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class),
                examples = @ExampleObject(
                    value = """
                    {
                        "endpoint": "/notifications/reservation",
                        "errors": {
                            "email": "El email no tiene un formato valido",
                            "title": "El titulo es obligatorio",
                            "body": "El cuerpo del mensaje es obligatorio"
                        },
                        "message": "Error de validacion en los campos",
                        "status": 400,
                        "timeStamp": "2026-06-12T05:11:58"
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor no controlado.",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class),
                examples = @ExampleObject(
                    value = """
                    {
                    "endpoint": "/notifications/reservation",
                        "errors": null,
                        "message": "Error interno del servidor",
                        "status": 500,
                        "timeStamp": "2026-06-12T05:11:58"
                    }
                    """
                )
            )
        ),
    })
    ResponseEntity<String> sendReservation(@Valid ReservationRequest request)
        throws ResendException;

    // compras
    @Operation(
        summary = "Envio correo de compra",
        description = "Envía un correo de confirmación de compra."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Correo enviado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = String.class),
                examples = @ExampleObject(
                    value = """
                    {
                    "Correo de compra enviado."
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Error de validación en los campos enviados (ej. email inválido, titulo vacio, lista de productos vacia).",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class),
                examples = @ExampleObject(
                    value = """
                    {
                        "endpoint": "/notifications/purchase",
                        "errors": {
                            "email": "El email no tiene un formato valido",
                            "title": "El titulo es obligatorio",
                            "products": "La lista de productos no puede estar vacia"
                        },
                        "message": "Error de validacion en los campos",
                        "status": 400,
                        "timeStamp": "2026-06-12T05:11:58"
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor no controlado.",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class),
                examples = @ExampleObject(
                    value = """
                    {
                    "endpoint": "/notifications/purchase",
                        "errors": null,
                        "message": "Error interno del servidor",
                        "status": 500,
                        "timeStamp": "2026-06-12T05:11:58"
                    }
                    """
                )
            )
        ),
    })
    ResponseEntity<String> sendPurchase(@Valid PurchaseRequest request)
        throws ResendException;
}
