package com.mariluz.notifications.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PurchaseRequest {

    private String email;
    private String title;
    private List<String> products;
}
