package com.mariluz.notifications.controller;

import com.mariluz.notifications.dto.PurchaseRequest;
import com.mariluz.notifications.dto.ReservationRequest;
import com.mariluz.notifications.service.EmailService;
import com.resend.core.exception.ResendException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController implements NotificationApi {

    private final EmailService service;

    @Autowired
    public NotificationController(EmailService service) {
        this.service = service;
    }

    // Reservas
    @Override
    @PostMapping("/reservation")
    public ResponseEntity<String> sendReservation(
        @RequestBody ReservationRequest request
    ) throws ResendException {
        // ResendException es checked, el GlobalExceptionHandler la gestiona en runtime
        service.sendReservationEmail(
            request.getEmail(),
            request.getTitle(),
            request.getBody(),
            Boolean.TRUE.equals(request.getCancellation()) // si llega como null lo dejamos como false
        );
        String status = Boolean.TRUE.equals(request.getCancellation())
            ? "cancelación"
            : "confirmación";
        return ResponseEntity.ok("Correo de " + status + " enviado.");
    }

    // compras
    @Override
    @PostMapping("/purchase")
    public ResponseEntity<String> sendPurchase(
        @RequestBody PurchaseRequest request
    ) throws ResendException {
        // ResendException es checked, el GlobalExceptionHandler la gestiona en runtime
        service.sendPurchaseEmail(
            request.getEmail(),
            request.getTitle(),
            request.getProducts()
        );
        return ResponseEntity.ok("Correo de compra enviado.");
    }
}
