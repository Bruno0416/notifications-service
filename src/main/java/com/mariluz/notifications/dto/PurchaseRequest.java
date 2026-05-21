package com.mariluz.notifications.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PurchaseRequest {

    @Email(message = "El email no tiene un formato valido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @NotBlank(message = "El titulo es obligatorio")
    private String title;

    @NotEmpty(message = "La lista de productos no puede estar vacia")
    private List<String> products;
}
