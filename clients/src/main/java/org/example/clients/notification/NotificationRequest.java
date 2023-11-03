package org.example.clients.notification;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationRequest {
    private String message;
    private String sender;
    private String sentAt;
    private String toCustomerEmail;
    private Integer toCustomerId;
}
