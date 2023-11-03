package org.example.customer;

import org.example.amqp.RabbitMQMessageProducer;
import org.example.clients.fraud.FraudCheckResponse;
import org.example.clients.fraud.FraudClient;
import org.example.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record CustomerService(
        CustomerRepository customerRepository,
        FraudClient fraudClient,
        RabbitMQMessageProducer producer
) {
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firsName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // todo: check if email void
        // todo: check if email not token
        customerRepository.saveAndFlush(customer); // save and flush to get id
        // check if it is fraud
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());
        if(fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }

        // send notification
        NotificationRequest notificationRequest = NotificationRequest.builder()
                .message(String.format("Hi %s, welcome to Amigoscode",
                        customer.getFirsName()))
                .sender("habscode")
                .sentAt("hello")
                .toCustomerId(customer.getId())
                .toCustomerEmail(customer.getEmail())
                .build();

       producer.publish(
               notificationRequest,
               "internal.exchange",
               "internal.notification.routing-key"
       );

    }
}
