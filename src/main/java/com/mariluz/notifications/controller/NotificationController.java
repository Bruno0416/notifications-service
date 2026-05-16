package com.mariluz.notifications.controller;

import com.mariluz.notifications.dto.PurchaseRequest;
import com.mariluz.notifications.dto.ReservationRequest;
import com.mariluz.notifications.service.EmailService;
import com.resend.core.exception.ResendException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final EmailService service;

    @Autowired
    public NotificationController(EmailService service) {
        this.service = service;
    }

    // Reservas
    @PostMapping("/reservation")
    public ResponseEntity<String> sendReservation(
        @Valid @RequestBody ReservationRequest request
    ) throws ResendException {
        boolean isCancel = Boolean.TRUE.equals(request.getIsCancellation()); // si llega como null lo dejamos como false

        service.sendReservationEmail(
            request.getEmail(),
            request.getTitle(),
            request.getBody(),
            isCancel
        );
        String status = request.getIsCancellation()
            ? "cancelación"
            : "confirmación";
        return ResponseEntity.ok("Correo de " + status + " envida.");
    }

    // compras
    @PostMapping("/purchase")
    public ResponseEntity<String> sendPurchase(
        @Valid @RequestBody PurchaseRequest request
    ) throws ResendException {
        service.sendPurchaseEmail(
            request.getEmail(),
            request.getTitle(),
            request.getProducts()
        );
        return ResponseEntity.ok("Correo de reserva envida.");
    }
}
