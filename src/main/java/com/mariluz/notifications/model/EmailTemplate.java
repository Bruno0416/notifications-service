package com.mariluz.notifications.model;

import java.util.List;

// Plantillas HTML para los emails
public class EmailTemplate {

    // Plantilla reserva -> confirmacion
    public static String buildReservationConfirmationHtml(
        String title,
        String body
    ) {
        return """
        <div style="font-family: Arial, sans-serif; max-width: 600px; margin: auto; padding: 20px; border: 1px solid #e0e0e0; border-radius: 10px; border-top: 5px solid #2ecc71;">
            <h2 style="color: #27ae60; text-align: center; text-transform: uppercase; letter-spacing: 1px;">%s</h2>

            <div style="background-color: #f0fdf4; padding: 20px; border-radius: 8px; margin-top: 20px; text-align: center;">
                <p style="font-size: 16px; color: #2c3e50; line-height: 1.6; margin: 0;">
                    %s
                </p>
            </div>

            <hr style="border: 0; border-top: 1px solid #eee; margin: 30px 0;">
            <p style="font-size: 12px; color: #95a5a6; text-align: center;">
                Este es un mensaje automático de confirmación. Por favor no respondas a este correo.
            </p>
        </div>
        """.formatted(title, body);
    }

    // 2. Plantilla reserva -> cancelación
    public static String buildReservationCancellationHtml(
        String title,
        String body
    ) {
        return """
        <div style="font-family: Arial, sans-serif; max-width: 600px; margin: auto; padding: 20px; border: 1px solid #e0e0e0; border-radius: 10px; border-top: 5px solid #e74c3c;">
            <h2 style="color: #c0392b; text-align: center; text-transform: uppercase; letter-spacing: 1px;">%s</h2>

            <div style="background-color: #fdf2f0; padding: 15px; border-radius: 8px; margin-top: 20px; border-left: 4px solid #e74c3c;">
                <p style="font-size: 16px; color: #2c3e50; line-height: 1.6; margin: 0;">
                    %s
                </p>
            </div>

            <hr style="border: 0; border-top: 1px solid #eee; margin: 30px 0;">
            <p style="font-size: 12px; color: #95a5a6; text-align: center;">
                Este es un mensaje automático de cancelación. Por favor no respondas a este correo.
            </p>
        </div>
        """.formatted(title, body);
    }

    // Plantilla para compras
    public static String buildPurchaseHtml(
        String title,
        List<String> products
    ) {
        StringBuilder productsListHtml = new StringBuilder(
            "<ul style='list-style-type: none; padding: 0;'>"
        );
        for (String product : products) {
            productsListHtml
                .append(
                    "<li style='padding: 10px; border-bottom: 1px solid #eee;'>✓ "
                )
                .append(product)
                .append("</li>");
        }
        productsListHtml.append("</ul>");

        return """
        <div style="font-family: Arial, sans-serif; max-width: 600px; margin: auto; padding: 20px; border: 1px solid #e0e0e0; border-radius: 10px;">
            <h2 style="color: #27ae60; text-align: center;">%s</h2>
            <p style="font-size: 16px; color: #34495e;">Aquí está el resumen de tu orden:</p>

            <div style="background-color: #f9f9f9; padding: 15px; border-radius: 5px; margin: 20px 0;">
                %s
            </div>

            <p style="font-size: 16px; color: #34495e;">¡Gracias por tu preferencia!</p>
            <hr style="border: 0; border-top: 1px solid #eee; margin: 30px 0;">
            <p style="font-size: 12px; color: #95a5a6; text-align: center;">
                Sistema automatizado de ventas.
            </p>
        </div>
        """.formatted(title, productsListHtml.toString());
    }
}
