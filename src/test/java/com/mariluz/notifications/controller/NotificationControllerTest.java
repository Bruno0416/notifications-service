package com.mariluz.notifications.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mariluz.notifications.dto.PurchaseRequest;
import com.mariluz.notifications.dto.ReservationRequest;
import com.mariluz.notifications.service.EmailServiceImpl;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.json.JsonMapper;

@WebMvcTest(NotificationController.class)
public class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonMapper objectMapper; // para mapear objetos/clases a json

    @MockitoBean
    private EmailServiceImpl service;

    // -------------- RESERVATION --------------
    // 200
    @Test
    public void testReservationNotification() throws Exception {
        // 1. preparar request
        ReservationRequest request = ReservationRequest.builder()
            .email("test@example.com")
            .title("Test Reservation")
            .body("Test body")
            .cancellation(false)
            .build();

        // 2. realizar request y verificar respuesta
        mockMvc
            .perform(
                post("/notifications/reservation")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            )
            .andExpect(status().isOk());
    }

    // 400
    @Test
    public void testReservationNotificationBadRequest() throws Exception {
        // 1. preparar request
        ReservationRequest request = ReservationRequest.builder()
            .email("testexample.com")
            .title("")
            .body("Test body")
            .cancellation(false)
            .build();

        // 2. realizar request y verificar respuesta
        mockMvc
            .perform(
                post("/notifications/reservation")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            )
            .andExpect(status().isBadRequest());
    }

    // -------------- PURCHASE --------------
    // 200
    @Test
    public void testPurchaseNotification() throws Exception {
        // 1. preparar request
        PurchaseRequest request = PurchaseRequest.builder()
            .email("test@example.com")
            .title("Test Reservation")
            .products(List.of("Test body"))
            .build();

        // 2. realizar request y verificar respuesta
        mockMvc
            .perform(
                post("/notifications/purchase")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            )
            .andExpect(status().isOk());
    }

    // 400
    @Test
    public void testPurchaseNotificationBadRequest() throws Exception {
        // 1. preparar request
        PurchaseRequest request = PurchaseRequest.builder()
            .email("testexample.com")
            .title("")
            .products(List.of())
            .build();

        // 2. realizar request y verificar respuesta
        mockMvc
            .perform(
                post("/notifications/purchase")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            )
            .andExpect(status().isBadRequest());
    }
}
