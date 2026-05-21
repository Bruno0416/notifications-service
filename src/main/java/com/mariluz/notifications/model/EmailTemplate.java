package com.mariluz.notifications.model;

import java.util.List;

// Plantillas HTML para los emails
public class EmailTemplate {

    private EmailTemplate() {
        throw new UnsupportedOperationException("utility class");
    }

    // Plantilla reserva -> confirmacion
    public static String buildReservationConfirmationHtml(
        String title,
        String body
    ) {
        return """
        <div style="font-family: 'Segoe UI', Arial, sans-serif; max-width: 620px; margin: 0 auto; background-color: #ffffff; border-radius: 12px; overflow: hidden; box-shadow: 0 4px 20px rgba(0,0,0,0.08);">
            <div style="background-color: #27ae60; background: linear-gradient(135deg, #1abc9c, #27ae60); padding: 40px; text-align: center;">
                <div style="font-size: 40px; margin-bottom: 14px;">&#10003;</div>
                <h1 style="color: #ffffff; margin: 0; font-size: 20px; font-weight: 700; letter-spacing: 1px; text-transform: uppercase;">%s</h1>
            </div>
            <div style="padding: 36px 40px 28px;">
                <div style="background-color: #f0fdf4; border-left: 4px solid #27ae60; border-radius: 0 8px 8px 0; padding: 20px 24px; margin-bottom: 28px;">
                    <p style="font-size: 15px; color: #2c3e50; line-height: 1.75; margin: 0;">%s</p>
                </div>
                <p style="font-size: 13px; color: #95a5a6; text-align: center; margin: 0;">¿Tienes alguna duda? No dudes en comunicarte con nosotros.</p>
            </div>
            <div style="background-color: #f8f9fa; border-top: 1px solid #e9ecef; padding: 18px 40px; text-align: center;">
                <p style="font-size: 11px; color: #b0bec5; margin: 0; line-height: 1.8;">Este es un mensaje automático de confirmación. Por favor no respondas a este correo.</p>
            </div>
        </div>
        """.formatted(title, body);
    }

    // 2. Plantilla reserva -> cancelación
    public static String buildReservationCancellationHtml(
        String title,
        String body
    ) {
        return """
        <div style="font-family: 'Segoe UI', Arial, sans-serif; max-width: 620px; margin: 0 auto; background-color: #ffffff; border-radius: 12px; overflow: hidden; box-shadow: 0 4px 20px rgba(0,0,0,0.08);">
            <div style="background-color: #c0392b; background: linear-gradient(135deg, #e74c3c, #c0392b); padding: 40px; text-align: center;">
                <div style="font-size: 40px; margin-bottom: 14px;">&#10005;</div>
                <h1 style="color: #ffffff; margin: 0; font-size: 20px; font-weight: 700; letter-spacing: 1px; text-transform: uppercase;">%s</h1>
            </div>
            <div style="padding: 36px 40px 28px;">
                <div style="background-color: #fdf2f0; border-left: 4px solid #e74c3c; border-radius: 0 8px 8px 0; padding: 20px 24px; margin-bottom: 28px;">
                    <p style="font-size: 15px; color: #2c3e50; line-height: 1.75; margin: 0;">%s</p>
                </div>
                <p style="font-size: 13px; color: #95a5a6; text-align: center; margin: 0;">Si tienes alguna consulta, contacta a nuestro equipo de atención.</p>
            </div>
            <div style="background-color: #f8f9fa; border-top: 1px solid #e9ecef; padding: 18px 40px; text-align: center;">
                <p style="font-size: 11px; color: #b0bec5; margin: 0; line-height: 1.8;">Este es un mensaje automático de cancelación. Por favor no respondas a este correo.</p>
            </div>
        </div>
        """.formatted(title, body);
    }

    // Plantilla para compras
    public static String buildPurchaseHtml(
        String title,
        List<String> products
    ) {
        StringBuilder productsListHtml = new StringBuilder(
            "<ul style='list-style-type: none; padding: 0; margin: 0;'>"
        );
        for (String product : products) {
            productsListHtml
                .append(
                    "<li style='padding: 12px 16px; border-bottom: 1px solid #e9ecef; font-size: 14px; color: #34495e;'>"
                )
                .append(
                    "<span style='color: #27ae60; font-weight: 700; margin-right: 8px;'>&#10003;</span>"
                )
                .append(product)
                .append("</li>");
        }
        productsListHtml.append("</ul>");

        return """
        <div style="font-family: 'Segoe UI', Arial, sans-serif; max-width: 620px; margin: 0 auto; background-color: #ffffff; border-radius: 12px; overflow: hidden; box-shadow: 0 4px 20px rgba(0,0,0,0.08);">
            <div style="background-color: #2980b9; background: linear-gradient(135deg, #3498db, #2980b9); padding: 40px; text-align: center;">
                <div style="font-size: 40px; margin-bottom: 14px;">&#128717;</div>
                <h1 style="color: #ffffff; margin: 0; font-size: 20px; font-weight: 700; letter-spacing: 1px; text-transform: uppercase;">%s</h1>
            </div>
            <div style="padding: 36px 40px 28px;">
                <p style="font-size: 15px; color: #34495e; margin: 0 0 16px 0;">Aquí está el resumen de tu orden:</p>
                <div style="background-color: #f8f9fa; border-radius: 8px; overflow: hidden; margin-bottom: 24px; border: 1px solid #e9ecef;">
                    %s
                </div>
                <div style="background-color: #eaf6ff; border-left: 4px solid #3498db; border-radius: 0 8px 8px 0; padding: 14px 18px;">
                    <p style="font-size: 14px; color: #2c3e50; margin: 0;">¡Gracias por tu preferencia! Tu pedido ha sido procesado correctamente.</p>
                </div>
            </div>
            <div style="background-color: #f8f9fa; border-top: 1px solid #e9ecef; padding: 18px 40px; text-align: center;">
                <p style="font-size: 11px; color: #b0bec5; margin: 0; line-height: 1.8;">Sistema automatizado de ventas.</p>
            </div>
        </div>
        """.formatted(title, productsListHtml.toString());
    }
}
