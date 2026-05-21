package com.mariluz.notifications.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ReservationRequest {

    @Email(message = "El email no tiene un formato valido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @NotBlank(message = "El titulo es obligatorio")
    private String title;

    @NotBlank(message = "El cuerpo del mensaje es obligatorio")
    private String body;

    @JsonProperty("isCancellation") // usamos JsonProperty para mapear el campo isCancellation correctamente
    private Boolean cancellation; // null se interpreta como false (no es cancelacion)
}
