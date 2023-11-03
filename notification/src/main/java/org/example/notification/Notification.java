package org.example.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Notification {
    @SequenceGenerator(
            name = "notification_sequence",
            sequenceName = "notification_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notification_sequence"
    )
    @Id
    private Integer notificationId;
    private String message;
    private String sender;
    private String sentAt;
    private String toCustomerEmail;
    private Integer toCustomerId;
}
