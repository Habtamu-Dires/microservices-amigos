package org.example.notification;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {

    private NotificationRepository repository;

    public void send(NotificationRequest notificationRequest) {
       Notification notification = Notification.builder()
               .sender(notificationRequest.getSender())
               .message(notificationRequest.getMessage())
               .toCustomerEmail(notificationRequest.getToCustomerEmail())
               .sentAt(notificationRequest.getSentAt())
               .toCustomerId(notificationRequest.getToCustomerId())
               .build();

       repository.save(notification);
    }

}
