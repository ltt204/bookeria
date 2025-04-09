package org.ltt204.profileservice.eventconsumer;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.ltt204.profileservice.events.consumers.UserCreatedEvent;
import org.ltt204.profileservice.events.consumers.UserDeletedEvent;
import org.ltt204.profileservice.events.consumers.UserUpdatedEvent;
import org.ltt204.profileservice.exception.AppException;
import org.ltt204.profileservice.exception.ErrorCode;
import org.ltt204.profileservice.service.interfaces.UserProfileService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class RabbitMQEventConsumer implements IEventConsumer {
    UserProfileService userProfileService;

    @Override
    @RabbitListener(queues = "${identity-service.identity.created.queue}")
    public void consumeEvent(UserCreatedEvent event) {
        try {
            log.info("Received UserCreatedEvent: {}", event);

            userProfileService.createUserProfileFromEvent(event);
        } catch (Exception e) {
            log.error("Error consuming UserCreatedEvent: {}", e.getMessage());
            throw new AppException(
                    ErrorCode.RABBITMQ_CONSUMER_ERROR,
                    String.format("Error consuming UserCreatedEvent with id %s", event.getId())
            );
        }
    }

    @Override
    public void consumeEvent(UserUpdatedEvent event) {
        try {
            log.info("Received UserUpdatedEvent: {}", event);


        } catch (Exception e) {
            log.error("Error consuming UserUpdatedEvent: {}", e.getMessage());
            throw new AppException(
                    ErrorCode.RABBITMQ_CONSUMER_ERROR,
                    String.format("Error consuming UserUpdatedEvent with id %s", event.getId())
            );
        }
    }

    @Override
    public void consumeEvent(UserDeletedEvent event) {
        try {
            log.info("Received UserDeletedEvent: {}", event);


        } catch (Exception e) {
            log.error("Error consuming UserDeletedEvent: {}", e.getMessage());
            throw new AppException(
                    ErrorCode.RABBITMQ_CONSUMER_ERROR,
                    String.format("Error consuming UserDeletedEvent with id %s", event.getId())
            );
        }
    }
}
