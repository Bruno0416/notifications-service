package com.mariluz.notifications.service;

import com.resend.core.exception.ResendException;
import java.util.List;

public interface EmailService {
    public void sendReservationEmail(
        String to,
        String title,
        String body,
        boolean isCancellation
    ) throws ResendException;

    public void sendPurchaseEmail(
        String to,
        String title,
        List<String> products
    ) throws ResendException;
}
