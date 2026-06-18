package com.mariluz.notifications.controller;

import com.mariluz.notifications.dto.PurchaseRequest;
import com.mariluz.notifications.dto.ReservationRequest;
import com.resend.core.exception.ResendException;
import org.springframework.http.ResponseEntity;

public interface NotificationApi {
    // Reservas
    ResponseEntity<String> sendReservation(ReservationRequest request)
        throws ResendException;

    // compras
    ResponseEntity<String> sendPurchase(PurchaseRequest request)
        throws ResendException;
}
