package com.mariluz.notifications.service;

import com.mariluz.notifications.model.EmailTemplate;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private Resend resend;

    @Value("${resend.mail.from}")
    private String fromAddress;

    // metodo enviar correo Reservas
    @Override
    public void sendReservationEmail(
        String email,
        String title,
        String body,
        boolean isCancellation
    ) throws ResendException {
        String htmlContent;
        if (isCancellation) {
            htmlContent = EmailTemplate.buildReservationCancellationHtml(
                title,
                body
            );
        } else {
            htmlContent = EmailTemplate.buildReservationConfirmationHtml(
                title,
                body
            );
        }
        sendEmail(email, title, htmlContent);
    }

    // metodo enviar compras
    @Override
    public void sendPurchaseEmail(
        String email,
        String title,
        List<String> products
    ) throws ResendException {
        String htmlContent = EmailTemplate.buildPurchaseHtml(title, products);
        sendEmail(email, title, htmlContent);
    }

    // construir correo (se comunica con la API Resend)
    private void sendEmail(String email, String subject, String htmlContent)
        throws ResendException {
        CreateEmailOptions params = CreateEmailOptions.builder()
            .from(fromAddress)
            .to(email)
            .subject(subject)
            .html(htmlContent)
            .build();

        resend.emails().send(params);
    }
}
