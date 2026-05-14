package com.mariluz.notifications.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ReservationRequest {

    private String email;
    private String title;
    private String body;

    @JsonProperty("isCancellation") // usamos JsonProperty para mapear el campo isCancellation correctamente
    private boolean isCancellation; // atributo para cambiar la template del correo en caso de ser

    public boolean getIsCancellation() {
        return isCancellation;
    }
}
